package com.ironhack.noveltyservice.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.noveltyservice.controller.dto.NoveltyInputDTO;
import com.ironhack.noveltyservice.model.ArticleEntity;
import com.ironhack.noveltyservice.model.CategoryEntity;
import com.ironhack.noveltyservice.model.NoveltyEntity;
import com.ironhack.noveltyservice.model.StockEntity;
import com.ironhack.noveltyservice.repository.ArticleRepository;
import com.ironhack.noveltyservice.repository.CategoryRepository;
import com.ironhack.noveltyservice.repository.NoveltyRepository;
import com.ironhack.noveltyservice.repository.StockRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class NoveltyServiceImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private NoveltyRepository noveltyRepository;

    @Autowired
    private StockRepository stockRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private ArticleEntity article;
    private NoveltyEntity novelty;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        CategoryEntity category = new CategoryEntity();
        category.setType("road bike");
        categoryRepository.save(category);

        article = new ArticleEntity();
        article.setCategory(category);
        article.setName("Belador 6");
        article.setBrand("Berria");
        article.setDescription("description test");
        article.setImageUrl("url image");
        article.setPrice(BigDecimal.valueOf(1799));
        articleRepository.save(article);

        novelty = new NoveltyEntity();
        novelty.setArticle(article);
        novelty.setCreationDate(LocalDate.now());
        novelty.setUserCreation("SpringBootTest");
        novelty.setModificationDate(LocalDate.of(1, 1, 1));
        novelty.setUserModification("");
        noveltyRepository.save(novelty);

        StockEntity stock = new StockEntity();
        stock.setArticle(article);
        stock.setSize("L");
        stock.setUnits((short) 1);
        stockRepository.save(stock);
    }

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
        noveltyRepository.deleteAll();
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void getAll_ReturnEmptyList_NoNoveltiesInDatabase() throws Exception {

        noveltyRepository.deleteAll();
        MvcResult mvcResult = mockMvc.perform(get("/novelties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("[]"));
    }

    @Test
    void getAll_ReturnData_NoveltiesInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/novelties")
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
    void store_UnprocessedEntity_NoveltyExistsInDatabase() throws Exception {

        NoveltyInputDTO noveltyInputDTO = new NoveltyInputDTO();
        noveltyInputDTO.setId(article.getId());
        String body = objectMapper.writeValueAsString(noveltyInputDTO);
        mockMvc.perform(post("/novelties")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_UnprocessedEntity_ArticleNotExistsInDatabase() throws Exception {

        stockRepository.deleteAll();
        noveltyRepository.deleteAll();
        articleRepository.deleteAll();
        NoveltyInputDTO noveltyInputDTO = new NoveltyInputDTO();
        noveltyInputDTO.setId(article.getId());
        String body = objectMapper.writeValueAsString(noveltyInputDTO);
        mockMvc.perform(post("/novelties")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_IsCreated_ArticleExistsInDatabase() throws Exception {

        noveltyRepository.deleteAll();
        NoveltyInputDTO noveltyInputDTO = new NoveltyInputDTO();
        noveltyInputDTO.setId(article.getId());
        String body = objectMapper.writeValueAsString(noveltyInputDTO);
        MvcResult mvcResult = mockMvc.perform(post("/novelties")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Belador 6"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("road bike"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Berria"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("L"));
        assertEquals(1, noveltyRepository.findAll().size());
    }

    @Test
    void delete_NotFound_NoveltyNotExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/novelties/0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_NoContent_NoveltyExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/novelties/" + novelty.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
        assertEquals(Optional.empty(), noveltyRepository.findById(novelty.getId()));
    }

    @Test
    void findByArticleId_NotFound_NoveltyNotExitsInDatabase() throws Exception {

        mockMvc.perform(get("/novelties-by-article-id?articleId=0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findByArticleId_ReturnData_NoveltyInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/novelties-by-article-id?articleId=" + article.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Belador 6"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("road bike"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Berria"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("description test"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("url image"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("1799.00"));
    }
}