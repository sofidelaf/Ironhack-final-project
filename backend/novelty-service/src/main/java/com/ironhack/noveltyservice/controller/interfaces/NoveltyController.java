package com.ironhack.noveltyservice.controller.interfaces;

import com.ironhack.noveltyservice.controller.dto.NoveltyOutputDTO;

import java.util.List;

public interface NoveltyController {

    List<NoveltyOutputDTO> getAll();
    NoveltyOutputDTO store(int id);
    void delete(int id);
}
