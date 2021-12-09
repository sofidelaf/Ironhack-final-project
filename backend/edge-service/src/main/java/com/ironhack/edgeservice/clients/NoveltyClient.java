package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.controller.dto.NoveltyInputDTO;
import com.ironhack.edgeservice.controller.dto.NoveltyOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("novelty-service")
public interface NoveltyClient {

    @GetMapping("/novelties")
    List<NoveltyOutputDTO> getAllNovelties();

    @PostMapping("/novelties")
    NoveltyOutputDTO storeNovelty(@RequestBody NoveltyInputDTO noveltyInputDTO);

    @DeleteMapping("/novelties/{id}")
    void deleteNovelty(@PathVariable(name = "id") int id);

    @GetMapping("/novelties-by-article-id")
    NoveltyOutputDTO findByArticleId(@RequestParam(name = "articleId") int articleId);
}
