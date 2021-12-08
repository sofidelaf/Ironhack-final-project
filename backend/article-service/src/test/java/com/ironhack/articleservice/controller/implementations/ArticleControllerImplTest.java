package com.ironhack.articleservice.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.articleservice.controller.dto.ArticleDTO;
import com.ironhack.articleservice.enums.ArticleSize;
import com.ironhack.articleservice.model.ArticleEntity;
import com.ironhack.articleservice.model.CategoryEntity;
import com.ironhack.articleservice.model.StockEntity;
import com.ironhack.articleservice.repository.ArticleRepository;
import com.ironhack.articleservice.repository.CategoryRepository;
import com.ironhack.articleservice.repository.StockRepository;
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

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ArticleControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private StockRepository stockRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private CategoryEntity category;
    private ArticleEntity article;
    private StockEntity stock;
    private ArticleDTO articleDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        category = new CategoryEntity();
        category.setType("road bike");
        category.setDescription("Bicycles built for traveling at speed on paved roads");
        category.setCreationDate(LocalDate.now());
        category.setUserCreation("SpringBootTest");
        category.setModificationDate(LocalDate.of(1, 1, 1));
        category.setUserModification("");
        categoryRepository.save(category);

        article = new ArticleEntity();
        article.setCategory(category);
        article.setName("Belador 6");
        article.setBrand("Berria");
        article.setDescription("description test");
        article.setImageUrl("url image");
        article.setPrice(BigDecimal.valueOf(1799));
        article.setCreationDate(LocalDate.now());
        article.setUserCreation("SpringBootTest");
        article.setModificationDate(LocalDate.of(1, 1, 1));
        article.setUserModification("");
        articleRepository.save(article);

        stock = new StockEntity();
        stock.setArticle(article);
        stock.setSize(ArticleSize.L);
        stock.setUnits((short) 1);
        stock.setCreationDate(LocalDate.now());
        stock.setUserCreation("SpringBootTest");
        stock.setModificationDate(LocalDate.of(1, 1, 1));
        stock.setUserModification("");
        stockRepository.save(stock);

        articleDTO = new ArticleDTO();
    }

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void store_UnprocessedEntity_InvalidBody() throws Exception {

        articleDTO.setName("");
        articleDTO.setCategory("");
        articleDTO.setBrand("");
        articleDTO.setDescription("");
        articleDTO.setImageUrl("");
        articleDTO.setPrice(BigDecimal.ZERO);
        articleDTO.setSize(ArticleSize.NON);
        articleDTO.setUnits((short) 0);

        String body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        articleDTO.setName("test");

        body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        articleDTO.setCategory("testing category");

        body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        articleDTO.setBrand("brand");

        body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        articleDTO.setDescription("Description");

        body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        articleDTO.setImageUrl("url image");

        body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        articleDTO.setUnits((short) 1);

        body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_UnprocessedEntity_CategoryNotExits() throws Exception {

        stockRepository.deleteAll();
        articleRepository.deleteAll();
        categoryRepository.deleteAll();

        articleDTO.setName("Name");
        articleDTO.setCategory("mountain bike");
        articleDTO.setBrand("Orbea");
        articleDTO.setDescription("Testing");
        articleDTO.setImageUrl("url image");
        articleDTO.setPrice(BigDecimal.valueOf(2990));
        articleDTO.setSize(ArticleSize.L);
        articleDTO.setUnits((short) 1);

        String body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_IsCreated_ValidBody() throws Exception {

        articleDTO.setName("Name");
        articleDTO.setCategory("road bike");
        articleDTO.setBrand("Orbea");
        articleDTO.setDescription("Testing");
        articleDTO.setImageUrl("url image");
        articleDTO.setPrice(BigDecimal.valueOf(2990));
        articleDTO.setSize(ArticleSize.L);
        articleDTO.setUnits((short) 1);

        String body = objectMapper.writeValueAsString(articleDTO);
        MvcResult mvcResult = mockMvc.perform(post("/articles")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Orbea"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Testing"));
        assertEquals(2, articleRepository.findAll().size());
    }

    @Test
    void getAll_ReturnEmptyList_NoArticlesInDatabase() throws Exception {

        stockRepository.deleteAll();
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
        MvcResult mvcResult = mockMvc.perform(get("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("[]"));
    }

    @Test
    void getAll_ReturnData_ArticlesInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Belador 6"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("road bike"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Berria"));
    }

    @Test
    void getAllByCategory_ReturnEmptyList_NoArticlesInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/articles?category=test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("[]"));
    }

    @Test
    void getAllByCategory_ReturnData_ArticlesInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/articles?category=road bike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Belador 6"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("road bike"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Berria"));
    }

    @Test
    void getByNameLike_UnprocessedEntity_NameParamNotInformed() throws Exception {

        mockMvc.perform(get("/articles-by-name?name=")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getByNameLike_ReturnEmptyList_NoArticlesInDatabase() throws Exception {

        stockRepository.deleteAll();
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
        MvcResult mvcResult = mockMvc.perform(get("/articles-by-name?name=pepe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("[]"));
    }

    @Test
    void getByNameLike_ReturnData_ArticlesInDatabase() throws Exception {

        article = new ArticleEntity();
        article.setCategory(category);
        article.setName("Belador 8");
        article.setBrand("Berria");
        article.setDescription("description test");
        article.setImageUrl("url image");
        article.setPrice(BigDecimal.valueOf(1899));
        article.setCreationDate(LocalDate.now());
        article.setUserCreation("SpringBootTest");
        article.setModificationDate(LocalDate.of(1, 1, 1));
        article.setUserModification("");
        articleRepository.save(article);

        article = new ArticleEntity();
        article.setCategory(category);
        article.setName("new Belador 33");
        article.setBrand("Berria");
        article.setDescription("description test");
        article.setImageUrl("url image");
        article.setPrice(BigDecimal.valueOf(1999));
        article.setCreationDate(LocalDate.now());
        article.setUserCreation("SpringBootTest");
        article.setModificationDate(LocalDate.of(1, 1, 1));
        article.setUserModification("");
        articleRepository.save(article);

        MvcResult mvcResult = mockMvc.perform(get("/articles-by-name?name=belador")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Belador 6"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("road bike"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Berria"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Belador 8"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("new Belador 33"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("1799"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("1899"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("1999"));
    }

    @Test
    void updatePrice_NotFound_ArticleNotExits() throws Exception {

        articleDTO.setPrice(BigDecimal.valueOf(2990));
        String body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(patch("/articles/0")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_NotFound_ArticleNotExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/articles/0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_NoContent_ArticleExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/articles/" + article.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
        assertEquals(Optional.empty(), articleRepository.findById(article.getId()));
    }

    @Test
    void updatePrice_UnprocessedEntity_EmptyBody() throws Exception {

        articleDTO.setPrice(null);
        String body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(patch("/articles/"+article.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updatePrice_NoContent_ArticleToUpdate() throws Exception {

        articleDTO.setPrice(BigDecimal.valueOf(2990));
        String body = objectMapper.writeValueAsString(articleDTO);
        mockMvc.perform(patch("/articles/"+article.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

        Optional<ArticleEntity> optionalArticle = articleRepository.findById(article.getId());
        assertTrue(optionalArticle.isPresent());
        assertEquals(BigDecimal.valueOf(2990.00).setScale(2), optionalArticle.get().getPrice());
    }
}