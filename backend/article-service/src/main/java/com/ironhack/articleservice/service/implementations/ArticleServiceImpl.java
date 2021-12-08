package com.ironhack.articleservice.service.implementations;

import com.ironhack.articleservice.controller.dto.ArticleDTO;
import com.ironhack.articleservice.controller.dto.ArticleOutputDTO;
import com.ironhack.articleservice.controller.dto.StockDTO;
import com.ironhack.articleservice.model.ArticleEntity;
import com.ironhack.articleservice.model.CategoryEntity;
import com.ironhack.articleservice.model.StockEntity;
import com.ironhack.articleservice.repository.ArticleRepository;
import com.ironhack.articleservice.repository.CategoryRepository;
import com.ironhack.articleservice.repository.StockRepository;
import com.ironhack.articleservice.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public ArticleDTO store(ArticleDTO articleDTO) {

        if ((articleDTO.getName().equals("")) || (articleDTO.getName().length() > 50)
                || (articleDTO.getCategory().equals("")) || (articleDTO.getCategory().length() > 25)
                || (articleDTO.getDescription().equals("")) || (articleDTO.getDescription().length() > 255)
                || (articleDTO.getBrand().equals("")) || (articleDTO.getBrand().length() > 50)
                || (articleDTO.getImageUrl().equals("")) || (articleDTO.getImageUrl().length() > 255)
                || (articleDTO.getUnits() == 0) || (articleDTO.getPrice().equals(BigDecimal.ZERO))
        ) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Body invalid");
        }

        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(articleDTO.getCategory());

        if (!optionalCategory.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Category not exist");
        }

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setCategory(optionalCategory.get());
        articleEntity.setName(articleDTO.getName());
        articleEntity.setBrand(articleDTO.getBrand());
        articleEntity.setDescription(articleDTO.getDescription());
        articleEntity.setImageUrl(articleDTO.getImageUrl());
        articleEntity.setPrice(articleDTO.getPrice());
        articleEntity.setCreationDate(LocalDate.now());
        articleEntity.setUserCreation("");
        articleEntity.setModificationDate(LocalDate.of(1,1,1));
        articleEntity.setUserModification("");
        articleRepository.save(articleEntity);

        StockEntity stockEntity = new StockEntity();
        stockEntity.setArticle(articleEntity);
        stockEntity.setSize(articleDTO.getSize());
        stockEntity.setUnits(articleDTO.getUnits());
        stockEntity.setCreationDate(LocalDate.now());
        stockEntity.setUserCreation("");
        stockEntity.setModificationDate(LocalDate.of(1,1,1));
        stockEntity.setUserModification("");
        stockRepository.save(stockEntity);

        ArticleDTO outputArticleDTO = new ArticleDTO();
        outputArticleDTO.setId(articleEntity.getId());
        outputArticleDTO.setName(articleEntity.getName());
        outputArticleDTO.setCategory(articleEntity.getCategory().getType());
        outputArticleDTO.setBrand(articleEntity.getBrand());
        outputArticleDTO.setDescription(articleEntity.getDescription());
        outputArticleDTO.setImageUrl(articleEntity.getImageUrl());
        outputArticleDTO.setPrice(articleEntity.getPrice());
        outputArticleDTO.setSize(stockEntity.getSize());
        outputArticleDTO.setUnits(stockEntity.getUnits());
        outputArticleDTO.setCreationDate(articleEntity.getCreationDate());
        outputArticleDTO.setUserCreation(articleEntity.getUserCreation());
        outputArticleDTO.setModificationDate(articleEntity.getModificationDate());
        outputArticleDTO.setUserModification(articleEntity.getUserModification());

        return outputArticleDTO;
    }

    @Override
    public List<ArticleOutputDTO> getAll() {

        List<ArticleEntity> articleDatabaseList = articleRepository.findAll();
        List<ArticleOutputDTO> outputList = new ArrayList<>();
        for (ArticleEntity article : articleDatabaseList) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO.setId(article.getId());
            articleOutputDTO.setName(article.getName());
            articleOutputDTO.setCategory(article.getCategory().getType());
            articleOutputDTO.setBrand(article.getBrand());
            articleOutputDTO.setDescription(article.getDescription());
            articleOutputDTO.setImageUrl(article.getImageUrl());
            articleOutputDTO.setPrice(article.getPrice());
            articleOutputDTO.setCreationDate(article.getCreationDate());
            articleOutputDTO.setUserCreation(article.getUserCreation());
            articleOutputDTO.setModificationDate(article.getModificationDate());
            articleOutputDTO.setUserModification(article.getUserModification());
            List<StockDTO> stockDTOList = new ArrayList<>();
            for (StockEntity stockEntity : article.getStockList()) {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setSize(stockEntity.getSize());
                stockDTO.setUnits(stockEntity.getUnits());
                stockDTOList.add(stockDTO);
            }
            articleOutputDTO.setStockList(stockDTOList);
            outputList.add(articleOutputDTO);
        }
        return outputList;
    }

    @Override
    public List<ArticleOutputDTO> getAllByCategory(String category) {

        List<ArticleEntity> articleDatabaseList = articleRepository.findByCategoryType(category);
        List<ArticleOutputDTO> outputList = new ArrayList<>();
        for (ArticleEntity article : articleDatabaseList) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO.setId(article.getId());
            articleOutputDTO.setName(article.getName());
            articleOutputDTO.setCategory(article.getCategory().getType());
            articleOutputDTO.setBrand(article.getBrand());
            articleOutputDTO.setDescription(article.getDescription());
            articleOutputDTO.setImageUrl(article.getImageUrl());
            articleOutputDTO.setPrice(article.getPrice());
            articleOutputDTO.setCreationDate(article.getCreationDate());
            articleOutputDTO.setUserCreation(article.getUserCreation());
            articleOutputDTO.setModificationDate(article.getModificationDate());
            articleOutputDTO.setUserModification(article.getUserModification());
            List<StockDTO> stockDTOList = new ArrayList<>();
            for (StockEntity stockEntity : article.getStockList()) {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setSize(stockEntity.getSize());
                stockDTO.setUnits(stockEntity.getUnits());
                stockDTOList.add(stockDTO);
            }
            articleOutputDTO.setStockList(stockDTOList);
            outputList.add(articleOutputDTO);
        }
        return outputList;
    }

    @Override
    public List<ArticleOutputDTO> getByNameLike(String name) {

        if (name.equals("")) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "name can not be empty");
        }

        List<ArticleEntity> articleDatabaseList = articleRepository.findByNameContainingIgnoreCase(name);
        List<ArticleOutputDTO> outputList = new ArrayList<>();
        for (ArticleEntity article : articleDatabaseList) {
            ArticleOutputDTO articleOutputDTO = new ArticleOutputDTO();
            articleOutputDTO.setId(article.getId());
            articleOutputDTO.setName(article.getName());
            articleOutputDTO.setCategory(article.getCategory().getType());
            articleOutputDTO.setBrand(article.getBrand());
            articleOutputDTO.setDescription(article.getDescription());
            articleOutputDTO.setImageUrl(article.getImageUrl());
            articleOutputDTO.setPrice(article.getPrice());
            articleOutputDTO.setCreationDate(article.getCreationDate());
            articleOutputDTO.setUserCreation(article.getUserCreation());
            articleOutputDTO.setModificationDate(article.getModificationDate());
            articleOutputDTO.setUserModification(article.getUserModification());
            List<StockDTO> stockDTOList = new ArrayList<>();
            for (StockEntity stockEntity : article.getStockList()) {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setSize(stockEntity.getSize());
                stockDTO.setUnits(stockEntity.getUnits());
                stockDTOList.add(stockDTO);
            }
            articleOutputDTO.setStockList(stockDTOList);
            outputList.add(articleOutputDTO);
        }
        return outputList;
    }

    @Override
    public void delete(int id) {

        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);

        if (!optionalArticle.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article does not exist");
        }
        for (StockEntity stock: optionalArticle.get().getStockList()) {
            stockRepository.delete(stock);
        }
        articleRepository.delete(optionalArticle.get());
    }

    @Override
    public void updatePrice(int id, ArticleDTO articleDTO) {

        if (articleDTO.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Not able to process");
        } else {
            Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);

            if (optionalArticle.isPresent()) {
                optionalArticle.get().setPrice(articleDTO.getPrice());
                optionalArticle.get().setModificationDate(LocalDate.now());
                articleRepository.save(optionalArticle.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
            }
        }
    }
}
