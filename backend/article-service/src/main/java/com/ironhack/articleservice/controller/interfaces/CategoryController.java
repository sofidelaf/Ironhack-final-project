package com.ironhack.articleservice.controller.interfaces;

import com.ironhack.articleservice.controller.dto.CategoryDTO;

import java.util.List;

public interface CategoryController {

    List<CategoryDTO> getAll();
    CategoryDTO store(CategoryDTO categoryDTO);
}
