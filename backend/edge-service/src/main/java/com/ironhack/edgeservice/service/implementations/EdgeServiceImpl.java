package com.ironhack.edgeservice.service.implementations;

import com.ironhack.edgeservice.clients.ArticleClient;
import com.ironhack.edgeservice.clients.DiscountClient;
import com.ironhack.edgeservice.clients.NoveltyClient;
import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.service.interfaces.EdgeService;
import feign.FeignException;
import feign.RetryableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EdgeServiceImpl implements EdgeService {

    @Autowired
    private ArticleClient articleClient;

    @Autowired
    private DiscountClient discountClient;

    @Autowired
    private NoveltyClient noveltyClient;

    private final Logger logger = LoggerFactory.getLogger(EdgeServiceImpl.class);

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
    public List<ArticleOutputDTO> getByNameLike(String name) {

        return articleClient.getByNameLike(name);
    }

    @Override
    public void deleteArticle(int id) {

        try {
            NoveltyOutputDTO noveltyOutputDTO = noveltyClient.findByArticleId(id);
            try {
                noveltyClient.deleteNovelty(noveltyOutputDTO.getId());
            } catch (RetryableException | FeignException.ServiceUnavailable e1) {
                logger.error(e1.getClass() + "");
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error in proxy service");
            } catch (FeignException.NotFound e2) {
                logger.info(e2.getClass() + "");
            }
        } catch (RetryableException | FeignException.ServiceUnavailable e1) {
            logger.error(e1.getClass() + "");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error in proxy service");
        } catch (FeignException.NotFound e2) {
            logger.info(e2.getClass() + "" + " deleteArticle " + id + " without novelty");
        }

        try {
            DiscountOutputDTO discountOutputDTO = discountClient.findByArticleId(id);
            try {
                discountClient.deleteDiscount(discountOutputDTO.getId());
            } catch (RetryableException | FeignException.ServiceUnavailable e1) {
                logger.error(e1.getClass() + "");
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error in proxy service");
            } catch (FeignException.NotFound e2) {
                logger.info(e2.getClass() + "");
            }
        } catch (RetryableException | FeignException.ServiceUnavailable e1) {
            logger.error(e1.getClass() + "");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error in proxy service");
        } catch (FeignException.NotFound e2) {
            logger.info(e2.getClass() + "" + " deleteArticle " + id + " without discount");
        }

        articleClient.delete(id);
    }

    @Override
    public void updatePrice(int id, ArticleDTO articleDTO) {

        articleClient.updatePrice(id, articleDTO);
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
