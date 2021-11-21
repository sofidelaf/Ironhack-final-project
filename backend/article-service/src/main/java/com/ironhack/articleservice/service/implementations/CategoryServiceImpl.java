package com.ironhack.articleservice.service.implementations;

import com.ironhack.articleservice.controller.dto.CategoryDTO;
import com.ironhack.articleservice.model.CategoryEntity;
import com.ironhack.articleservice.repository.CategoryRepository;
import com.ironhack.articleservice.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAll() {

        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoryDTO> outputList = new ArrayList<>();
        for (CategoryEntity category : categoryEntityList) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setType(category.getType());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setCreationDate(category.getCreationDate());
            categoryDTO.setUserCreation(category.getUserCreation());
            categoryDTO.setModificationDate(category.getModificationDate());
            categoryDTO.setUserModification(category.getUserModification());
            outputList.add(categoryDTO);
        }
        return outputList;
    }

    @Override
    public CategoryDTO store(CategoryDTO categoryDTO) {

        if ((categoryDTO.getType().equals("")) || (categoryDTO.getDescription().equals(""))
        || (categoryDTO.getType().length() > 25) || (categoryDTO.getDescription().length() > 255)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Body invalid");
        }

        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryDTO.getType());

        if (optionalCategoryEntity.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Category exits");
        }

        CategoryEntity categoryDatabase = new CategoryEntity();
        categoryDatabase.setType(categoryDTO.getType());
        categoryDatabase.setDescription(categoryDTO.getDescription());
        categoryDatabase.setCreationDate(LocalDate.now());
        categoryDatabase.setUserCreation("");
        categoryDatabase.setModificationDate(LocalDate.of(1,1,1));
        categoryDatabase.setUserModification("");
        categoryRepository.save(categoryDatabase);

        CategoryDTO outputCategoryDTO = new CategoryDTO();
        outputCategoryDTO.setType(categoryDatabase.getType());
        outputCategoryDTO.setDescription(categoryDatabase.getDescription());
        outputCategoryDTO.setCreationDate(categoryDatabase.getCreationDate());
        outputCategoryDTO.setUserCreation(categoryDatabase.getUserCreation());
        outputCategoryDTO.setModificationDate(categoryDatabase.getModificationDate());
        outputCategoryDTO.setUserModification(categoryDatabase.getUserModification());

        return outputCategoryDTO;
    }
}
