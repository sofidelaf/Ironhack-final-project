package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.controller.dto.*;

import java.util.List;

public interface EdgeService {

    ArticleDTO storeArticle(ArticleDTO articleDTO);
    List<ArticleOutputDTO> getAllArticles();
    List<ArticleOutputDTO> getAllArticlesByCategory(String category);
    List<CategoryDTO> getAllCategories();
    CategoryDTO storeCategory(CategoryDTO categoryDTO);
    List<DiscountOutputDTO> getAllDiscounts();
    DiscountOutputDTO storeDiscount(DiscountInputDTO discountInputDTO);
    void deleteDiscount(int id);
    List<NoveltyOutputDTO> getAllNovelties();
    NoveltyOutputDTO storeNovelty(NoveltyInputDTO noveltyInputDTO);
    void deleteNovelty(int id);
}
