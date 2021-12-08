package com.ironhack.noveltyservice.controller.interfaces;

import com.ironhack.noveltyservice.controller.dto.NoveltyInputDTO;
import com.ironhack.noveltyservice.controller.dto.NoveltyOutputDTO;

import java.util.List;

public interface NoveltyController {

    List<NoveltyOutputDTO> getAll();
    NoveltyOutputDTO store(NoveltyInputDTO noveltyInputDTO);
    void delete(int id);
    NoveltyOutputDTO findByArticleId(int articleId);
}
