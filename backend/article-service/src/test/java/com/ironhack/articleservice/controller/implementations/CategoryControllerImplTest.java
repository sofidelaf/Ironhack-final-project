package com.ironhack.articleservice.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.articleservice.controller.dto.CategoryDTO;
import com.ironhack.articleservice.model.CategoryEntity;
import com.ironhack.articleservice.repository.CategoryRepository;
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
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CategoryControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CategoryRepository categoryRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        CategoryEntity categoryOne = new CategoryEntity();
        categoryOne.setType("mountain bike");
        categoryOne.setDescription("Bicycle designed for off-road cycling. Mountain bikes share some similarities with " +
                "other bicycles, but incorporate features designed to enhance durability and performance in rough terrain, " +
                "which makes them heavy");
        categoryOne.setCreationDate(LocalDate.now());
        categoryOne.setUserCreation("SpringBootTest");
        categoryOne.setModificationDate(LocalDate.of(1,1,1));
        categoryOne.setUserModification("");
        categoryRepository.save(categoryOne);

        CategoryEntity categoryTwo = new CategoryEntity();
        categoryTwo.setType("road bike");
        categoryTwo.setDescription("Bicycles built for traveling at speed on paved roads");
        categoryTwo.setCreationDate(LocalDate.now());
        categoryTwo.setUserCreation("SpringBootTest");
        categoryTwo.setModificationDate(LocalDate.of(1,1,1));
        categoryTwo.setUserModification("");
        categoryRepository.save(categoryTwo);
    }

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void getAll_ReturnEmptyList_NoCategories() throws Exception {

        categoryRepository.deleteAll();
        MvcResult mvcResult = mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("[]"));
    }

    @Test
    void getAll_ReturnCategoryList_CategoriesInDatabase() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("mountain bike"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("SpringBootTest"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("road bike"));
        assertEquals(0, categoryRepository.findById("mountain bike").get().getArticleList().size());
    }

    @Test
    void store_UnprocessedEntity_BodyInvalid() throws Exception {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setType("");
        categoryDTO.setDescription("");
        String body = objectMapper.writeValueAsString(categoryDTO);
        mockMvc.perform(post("/categories")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        categoryDTO.setType("bike");
        body = objectMapper.writeValueAsString(categoryDTO);
        mockMvc.perform(post("/categories")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        categoryDTO.setType("");
        categoryDTO.setDescription("mountain bike");
        body = objectMapper.writeValueAsString(categoryDTO);
        mockMvc.perform(post("/categories")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        categoryDTO.setType("this is a large type123456");
        categoryDTO.setDescription("mountain bike");
        body = objectMapper.writeValueAsString(categoryDTO);
        mockMvc.perform(post("/categories")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        categoryDTO.setType("test");
        categoryDTO.setDescription("Bicycle designed for off-road cycling. Mountain bikes share some similarities with " +
                "other bicycles, but incorporate features designed to enhance durability and performance in rough terrain, " +
                "which makes them heavy. A large description to fail stored database");
        body = objectMapper.writeValueAsString(categoryDTO);
        mockMvc.perform(post("/categories")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_UnprocessedEntity_CategoryExits() throws Exception {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setType("mountain bike");
        categoryDTO.setDescription("mountain bike");
        String body = objectMapper.writeValueAsString(categoryDTO);
        mockMvc.perform(post("/categories")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_IsCreated_ValidBody() throws Exception {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setType("tools");
        categoryDTO.setDescription("test description");
        String body = objectMapper.writeValueAsString(categoryDTO);
        MvcResult mvcResult = mockMvc.perform(post("/categories")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("tools"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("test description"));
        assertEquals(3, categoryRepository.findAll().size());
        assertEquals(categoryDTO.getDescription(), categoryRepository.findById(categoryDTO.getType()).get().getDescription());
    }
}