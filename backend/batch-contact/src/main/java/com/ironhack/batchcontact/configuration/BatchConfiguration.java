package com.ironhack.batchcontact.configuration;

import com.ironhack.batchcontact.processor.ContactItemProcessor;
import com.ironhack.batchcontact.model.Contact;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.support.ListPreparedStatementSetter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/bicisAntidio_admin");
        dataSource.setUsername("ironhacker");
        dataSource.setPassword("Ir0nh4ck3r!");

        return dataSource;
    }

    @Bean
    public JdbcCursorItemReader<Contact> reader() {
        JdbcCursorItemReader<Contact> reader = new JdbcCursorItemReader<Contact>();
        reader.setDataSource(dataSource);

        List<Timestamp> parameters = new ArrayList<>();
        parameters.add(Timestamp.valueOf(LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth() - 1, 0, 0, 0)));
        parameters.add(Timestamp.valueOf(LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth() - 1, 23, 59, 59)));
        ListPreparedStatementSetter pss = new ListPreparedStatementSetter(parameters);
        reader.setPreparedStatementSetter(pss);
        reader.setSql("SELECT id, full_name, email, subject, details, creation_datetime FROM contact " +
                "WHERE (creation_datetime >= ? AND creation_datetime <= ?)");
        reader.setRowMapper(new ContactRowMapper());

        return reader;
    }

    public class ContactRowMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {

            Contact contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setFullName(rs.getString("full_name"));
            contact.setEmail(rs.getString("email"));
            contact.setSubject(rs.getString("subject"));
            contact.setDetails(rs.getString("details"));
            contact.setCreationDatetime(rs.getTimestamp("creation_datetime"));
            return contact;
        }
    }

    @Bean
    public ContactItemProcessor processor() {

        return new ContactItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<Contact> writer() {

        FlatFileItemWriter<Contact> writer = new FlatFileItemWriter<Contact>();
        writer.setResource(new ClassPathResource("contact" + ".csv"));
        writer.setLineAggregator(new DelimitedLineAggregator<>(){{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<>(){{
                setNames(new String[] { "id", "fullName", "email", "subject", "details", "creationDatetime"});
            }});
        }});

        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("Step 1").<Contact, Contact> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job exportContactJob() {

        return jobBuilderFactory.get("exportContactJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
}
