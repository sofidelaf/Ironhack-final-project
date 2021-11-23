package com.ironhack.edgeservice.service.implementations;

import com.ironhack.edgeservice.clients.ArticleClient;
import com.ironhack.edgeservice.clients.DiscountClient;
import com.ironhack.edgeservice.clients.NoveltyClient;
import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.service.interfaces.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdgeServiceImpl implements EdgeService {

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private DiscountClient discountClient;

    @Autowired
    private NoveltyClient noveltyClient;

    @Override
    public ArticleDTO storeArticle(ArticleDTO articleDTO) {

        return articleClient.storeArticle(articleDTO);
    }

    @Override
    public List<ArticleOutputDTO> getAllArticles() {

        return articleClient.getAllArticles(null);
    }

    @Override
    public List<ArticleOutputDTO> getAllArticlesByCategory(String category) {

        return articleClient.getAllArticles(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return articleClient.getAllCategories();
    }

    @Override
    public CategoryDTO storeCategory(CategoryDTO categoryDTO) {

        return articleClient.storeCategory(categoryDTO);
    }

    @Override
    public List<DiscountOutputDTO> getAllDiscounts() {

        return discountClient.getAllDiscounts();
    }

    @Override
    public DiscountOutputDTO storeDiscount(DiscountInputDTO discountInputDTO) {

        return discountClient.storeDiscount(discountInputDTO);
    }

    @Override
    public void deleteDiscount(int id) {

        discountClient.deleteDiscount(id);
    }

    @Override
    public List<NoveltyOutputDTO> getAllNovelties() {

        return noveltyClient.getAllNovelties();
    }

    @Override
    public NoveltyOutputDTO storeNovelty(NoveltyInputDTO noveltyInputDTO) {

        return noveltyClient.storeNovelty(noveltyInputDTO);
    }

    @Override
    public void deleteNovelty(int id) {

        noveltyClient.deleteNovelty(id);
    }
}
