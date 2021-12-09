package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.controller.dto.*;

import java.util.List;
import java.util.Optional;

public interface EdgeController {

    ArticleDTO storeArticle(ArticleDTO articleDTO);
    List<ArticleOutputDTO> getAllArticles(Optional<String> category);
    List<CategoryDTO> getAllCategories();
    List<ArticleOutputDTO> getByNameLike(String name);
    void deleteArticle(int id);
    void updatePrice(int id, ArticleDTO articleDTO);
    CategoryDTO storeCategory(CategoryDTO categoryDTO);
    List<DiscountOutputDTO> getAllDiscounts();
    DiscountOutputDTO storeDiscount(DiscountInputDTO discountInputDTO);
    void deleteDiscount(int id);
    List<NoveltyOutputDTO> getAllNovelties();
    NoveltyOutputDTO storeNovelty(NoveltyInputDTO noveltyInputDTO);
    void deleteNovelty(int id);
}
