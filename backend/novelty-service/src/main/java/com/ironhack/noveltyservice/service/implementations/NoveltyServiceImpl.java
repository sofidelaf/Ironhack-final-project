package com.ironhack.noveltyservice.service.implementations;

import com.ironhack.noveltyservice.controller.dto.NoveltyOutputDTO;
import com.ironhack.noveltyservice.controller.dto.StockDTO;
import com.ironhack.noveltyservice.model.ArticleEntity;
import com.ironhack.noveltyservice.model.NoveltyEntity;
import com.ironhack.noveltyservice.model.StockEntity;
import com.ironhack.noveltyservice.repository.ArticleRepository;
import com.ironhack.noveltyservice.repository.NoveltyRepository;
import com.ironhack.noveltyservice.service.interfaces.NoveltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoveltyServiceImpl implements NoveltyService {

    @Autowired
    private NoveltyRepository noveltyRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<NoveltyOutputDTO> getAll() {

        List<NoveltyEntity> noveltyEntityList = noveltyRepository.findAll();
        List<NoveltyOutputDTO> outputList = new ArrayList<>();
        for (NoveltyEntity novelty : noveltyEntityList) {
            NoveltyOutputDTO noveltyDTO = new NoveltyOutputDTO();
            noveltyDTO.setId(novelty.getId());
            noveltyDTO.setCategory(novelty.getArticle().getCategory().getType());
            noveltyDTO.setName(novelty.getArticle().getName());
            noveltyDTO.setBrand(novelty.getArticle().getBrand());
            noveltyDTO.setDescription(novelty.getArticle().getDescription());
            noveltyDTO.setImageUrl(novelty.getArticle().getImageUrl());
            noveltyDTO.setPrice(novelty.getArticle().getPrice());
            List<StockDTO> stockDTOList = new ArrayList<>();
            for (StockEntity stock : novelty.getArticle().getStockList()) {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setSize(stock.getSize());
                stockDTO.setUnits(stock.getUnits());
                stockDTOList.add(stockDTO);
            }
            noveltyDTO.setStockList(stockDTOList);

            outputList.add(noveltyDTO);
        }
        return outputList;
    }

    @Override
    public NoveltyOutputDTO store(int id) {

        Optional<ArticleEntity> optionalArticle = articleRepository.findById(id);

        if (!optionalArticle.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are not article with that id");
        }

        Optional<NoveltyEntity> optionalNovelty = noveltyRepository.findByArticleId(id);

        if (optionalNovelty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Novelty exists in system");
        }

        NoveltyEntity noveltyDatabase = new NoveltyEntity();
        noveltyDatabase.setArticle(optionalArticle.get());
        noveltyDatabase.setCreationDate(LocalDate.now());
        noveltyDatabase.setUserCreation("");
        noveltyDatabase.setModificationDate(LocalDate.of(1, 1, 1));
        noveltyDatabase.setUserModification("");
        noveltyRepository.save(noveltyDatabase);

        NoveltyOutputDTO outputNovelty = new NoveltyOutputDTO();
        outputNovelty.setId(noveltyDatabase.getId());
        outputNovelty.setCategory(noveltyDatabase.getArticle().getCategory().getType());
        outputNovelty.setName(noveltyDatabase.getArticle().getName());
        outputNovelty.setBrand(noveltyDatabase.getArticle().getBrand());
        outputNovelty.setDescription(noveltyDatabase.getArticle().getDescription());
        outputNovelty.setImageUrl(noveltyDatabase.getArticle().getImageUrl());
        outputNovelty.setPrice(noveltyDatabase.getArticle().getPrice());
        List<StockDTO> stockDTOList = new ArrayList<>();
        for (StockEntity stock : noveltyDatabase.getArticle().getStockList()) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setSize(stock.getSize());
            stockDTO.setUnits(stock.getUnits());
            stockDTOList.add(stockDTO);
        }
        outputNovelty.setStockList(stockDTOList);

        return outputNovelty;
    }

    @Override
    public void delete(int id) {

        Optional<NoveltyEntity> optionalNovelty = noveltyRepository.findById(id);

        if (!optionalNovelty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Novelty does not exist");
        }
        noveltyRepository.delete(optionalNovelty.get());
    }

    @Override
    public NoveltyOutputDTO findByArticleId(int articleId) {

        Optional<NoveltyEntity> optionalNovelty = noveltyRepository.findByArticleId(articleId);
        if (!optionalNovelty.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Novelty does not exist");
        }

        NoveltyOutputDTO outputNovelty = new NoveltyOutputDTO();
        outputNovelty.setId(optionalNovelty.get().getId());
        outputNovelty.setCategory(optionalNovelty.get().getArticle().getCategory().getType());
        outputNovelty.setName(optionalNovelty.get().getArticle().getName());
        outputNovelty.setBrand(optionalNovelty.get().getArticle().getBrand());
        outputNovelty.setDescription(optionalNovelty.get().getArticle().getDescription());
        outputNovelty.setImageUrl(optionalNovelty.get().getArticle().getImageUrl());
        outputNovelty.setPrice(optionalNovelty.get().getArticle().getPrice());
        List<StockDTO> stockDTOList = new ArrayList<>();
        for (StockEntity stock : optionalNovelty.get().getArticle().getStockList()) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setSize(stock.getSize());
            stockDTO.setUnits(stock.getUnits());
            stockDTOList.add(stockDTO);
        }
        outputNovelty.setStockList(stockDTOList);

        return outputNovelty;
    }
}
