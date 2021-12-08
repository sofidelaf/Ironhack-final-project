package com.ironhack.articleservice.service.interfaces;

import com.ironhack.articleservice.controller.dto.ArticleDTO;
import com.ironhack.articleservice.controller.dto.ArticleOutputDTO;

import java.util.List;

public interface ArticleService {

    ArticleDTO store(ArticleDTO articleDTO);
    List<ArticleOutputDTO> getAll();
    List<ArticleOutputDTO> getAllByCategory(String category);
    List<ArticleOutputDTO> getByNameLike(String name);
    void delete(int id);
    void updatePrice(int id, ArticleDTO articleDTO);
}
