package com.ironhack.articleservice.controller.implementations;

import com.ironhack.articleservice.controller.dto.CategoryDTO;
import com.ironhack.articleservice.controller.interfaces.CategoryController;
import com.ironhack.articleservice.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryControllerImpl implements CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Override
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAll() {

        return categoryService.getAll();
    }

    @Override
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO store(@RequestBody CategoryDTO categoryDTO) {

        return categoryService.store(categoryDTO);
    }
}
