package com.ironhack.discountservice.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.discountservice.controller.dto.DiscountInputDTO;
import com.ironhack.discountservice.model.ArticleEntity;
import com.ironhack.discountservice.model.CategoryEntity;
import com.ironhack.discountservice.model.DiscountEntity;
import com.ironhack.discountservice.model.StockEntity;
import com.ironhack.discountservice.repository.ArticleRepository;
import com.ironhack.discountservice.repository.CategoryRepository;
import com.ironhack.discountservice.repository.DiscountRepository;
import com.ironhack.discountservice.repository.StockRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DiscountControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private StockRepository stockRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private ArticleEntity article;
    private DiscountEntity discount;

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

        discount = new DiscountEntity();
        discount.setArticle(article);
        discount.setPromotion("Christmas");
        discount.setQuantity(BigDecimal.TEN);
        discount.setCreationDate(LocalDate.now());
        discount.setUserCreation("SpringBootTest");
        discount.setModificationDate(LocalDate.of(1, 1, 1));
        discount.setUserModification("");
        discountRepository.save(discount);

        StockEntity stock = new StockEntity();
        stock.setArticle(article);
        stock.setSize("L");
        stock.setUnits((short) 1);
        stockRepository.save(stock);
    }

    @AfterEach
    void tearDown() {
        stockRepository.deleteAll();
        discountRepository.deleteAll();
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void getAll_ReturnEmptyList_NoDiscountsInDatabase() throws Exception {

        discountRepository.deleteAll();
        MvcResult mvcResult = mockMvc.perform(get("/discounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("[]"));
    }

    @Test
    void getAll_ReturnData_DiscountsInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/discounts")
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
    void store_UnprocessedEntity_BodyInvalid() throws Exception {

        DiscountInputDTO discountInputDTO = new DiscountInputDTO();
        discountInputDTO.setId(article.getId());
        discountInputDTO.setPromotion("");
        discountInputDTO.setQuantity(BigDecimal.TEN);
        String body = objectMapper.writeValueAsString(discountInputDTO);
        mockMvc.perform(post("/discounts")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        discountInputDTO.setId(article.getId());
        discountInputDTO.setPromotion("test");
        discountInputDTO.setQuantity(BigDecimal.ZERO);
        body = objectMapper.writeValueAsString(discountInputDTO);
        mockMvc.perform(post("/discounts")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_UnprocessedEntity_DiscountExistsInDatabase() throws Exception {

        DiscountInputDTO discountInputDTO = new DiscountInputDTO();
        discountInputDTO.setId(article.getId());
        discountInputDTO.setPromotion("test");
        discountInputDTO.setQuantity(BigDecimal.TEN);
        String body = objectMapper.writeValueAsString(discountInputDTO);
        mockMvc.perform(post("/discounts")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_UnprocessedEntity_ArticleNotExistsInDatabase() throws Exception {

        stockRepository.deleteAll();
        discountRepository.deleteAll();
        articleRepository.deleteAll();
        DiscountInputDTO discountInputDTO = new DiscountInputDTO();
        discountInputDTO.setId(article.getId());
        discountInputDTO.setPromotion("test");
        discountInputDTO.setQuantity(BigDecimal.TEN);
        String body = objectMapper.writeValueAsString(discountInputDTO);
        mockMvc.perform(post("/discounts")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_IsCreated_ArticleExistsInDatabase() throws Exception {

        discountRepository.deleteAll();
        DiscountInputDTO discountInputDTO = new DiscountInputDTO();
        discountInputDTO.setId(article.getId());
        discountInputDTO.setPromotion("Christmas");
        discountInputDTO.setQuantity(BigDecimal.TEN);
        String body = objectMapper.writeValueAsString(discountInputDTO);
        MvcResult mvcResult = mockMvc.perform(post("/discounts")
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
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Christmas"));
        assertEquals(1, discountRepository.findAll().size());
    }

    @Test
    void delete_NotFound_DiscountNotExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/discounts/0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_NoContent_DiscountExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/discounts/" + discount.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
        assertEquals(Optional.empty(), discountRepository.findById(discount.getId()));
    }

    @Test
    void findByArticleId_NotFound_DiscountNotExitsInDatabase() throws Exception {

        mockMvc.perform(get("/discounts-by-article-id?articleId=0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void findByArticleId_ReturnData_DiscountInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/discounts-by-article-id?articleId=" + article.getId())
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
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("Christmas"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("10.00"));
    }
}