package com.ironhack.articleservice.controller.interfaces;

import com.ironhack.articleservice.controller.dto.ArticleDTO;
import com.ironhack.articleservice.controller.dto.ArticleOutputDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleController {

    ArticleDTO store(ArticleDTO articleDTO);
    List<ArticleOutputDTO> getAll(Optional<String> category);
    List<ArticleOutputDTO> getByNameLike(String name);
    void delete(int id);
    void updatePrice(int id, ArticleDTO articleDTO);
}
