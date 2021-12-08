package com.ironhack.noveltyservice.service.interfaces;

import com.ironhack.noveltyservice.controller.dto.NoveltyOutputDTO;

import java.util.List;

public interface NoveltyService {

    List<NoveltyOutputDTO> getAll();
    NoveltyOutputDTO store(int id);
    void delete(int id);
    NoveltyOutputDTO findByArticleId(int articleId);
}
