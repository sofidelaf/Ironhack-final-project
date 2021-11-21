package com.ironhack.articleservice.service.interfaces;

import com.ironhack.articleservice.controller.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAll();
    CategoryDTO store(CategoryDTO categoryDTO);
}
