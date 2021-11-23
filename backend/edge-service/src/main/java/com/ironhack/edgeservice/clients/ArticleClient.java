package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.controller.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("article-service")
public interface ArticleClient {

    @PostMapping("/articles")
    ArticleDTO storeArticle(@RequestBody ArticleDTO articleDTO);

    @GetMapping("/articles")
    List<ArticleOutputDTO> getAllArticles(@RequestParam(name = "category") String category);

    @GetMapping("/categories")
    List<CategoryDTO> getAllCategories();

    @PostMapping("/categories")
    CategoryDTO storeCategory(@RequestBody CategoryDTO categoryDTO);

}
