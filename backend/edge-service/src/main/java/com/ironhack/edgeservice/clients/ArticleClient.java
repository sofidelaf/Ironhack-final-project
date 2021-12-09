package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.controller.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("article-service")
public interface ArticleClient {

    @PostMapping("/articles")
    ArticleDTO storeArticle(@RequestBody ArticleDTO articleDTO);

    @GetMapping("/articles")
    List<ArticleOutputDTO> getAllArticles(@RequestParam(name = "category") String category);

    @GetMapping("/articles-by-name")
    List<ArticleOutputDTO> getByNameLike(@RequestParam("name") String name);

    @DeleteMapping("/articles/{id}")
    void delete(@PathVariable(name = "id") int id);

    @PatchMapping("/articles/{id}")
    void updatePrice(@PathVariable(name = "id") int id, @RequestBody ArticleDTO articleDTO);

    @GetMapping("/categories")
    List<CategoryDTO> getAllCategories();

    @PostMapping("/categories")
    CategoryDTO storeCategory(@RequestBody CategoryDTO categoryDTO);

}
