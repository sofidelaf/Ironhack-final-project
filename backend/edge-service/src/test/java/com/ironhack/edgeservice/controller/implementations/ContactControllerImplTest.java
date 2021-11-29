package com.ironhack.edgeservice.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.controller.dto.ContactDTO;
import com.ironhack.edgeservice.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ContactControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactRepository contactRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private ContactDTO contactDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        contactDTO = new ContactDTO();
        contactDTO.setFullName("Pepito");
        contactDTO.setEmail("test@email.com");
        contactDTO.setSubject("Springboot test");
        contactDTO.setDetails("lorem bla bla bla");
    }

    @AfterEach
    void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    void store_UnprocessedEntity_InvalidBody() throws Exception {

        contactDTO.setFullName("");
        String body = objectMapper.writeValueAsString(contactDTO);
        mockMvc.perform(post("/contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        contactDTO.setFullName("Pepito");
        contactDTO.setEmail("");
        body = objectMapper.writeValueAsString(contactDTO);
        mockMvc.perform(post("/contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        contactDTO.setEmail("test@email.com");
        contactDTO.setSubject("");
        body = objectMapper.writeValueAsString(contactDTO);
        mockMvc.perform(post("/contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        contactDTO.setSubject("Springboot test");
        contactDTO.setDetails("");
        body = objectMapper.writeValueAsString(contactDTO);
        mockMvc.perform(post("/contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_IsCreated_MessageContactInsertInDatabase() throws Exception {

        String body = objectMapper.writeValueAsString(contactDTO);
        mockMvc.perform(post("/contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isCreated());
        assertEquals(1, contactRepository.findAll().size());
    }
}