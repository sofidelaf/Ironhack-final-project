package com.ironhack.discountservice.service.implementations;

import com.ironhack.discountservice.controller.dto.DiscountInputDTO;
import com.ironhack.discountservice.controller.dto.DiscountOutputDTO;
import com.ironhack.discountservice.controller.dto.StockDTO;
import com.ironhack.discountservice.model.ArticleEntity;
import com.ironhack.discountservice.model.DiscountEntity;
import com.ironhack.discountservice.model.StockEntity;
import com.ironhack.discountservice.repository.ArticleRepository;
import com.ironhack.discountservice.repository.DiscountRepository;
import com.ironhack.discountservice.service.interfaces.DiscountService;
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
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<DiscountOutputDTO> getAll() {

        List<DiscountEntity> discountEntityList = discountRepository.findAll();
        List<DiscountOutputDTO> outputList = new ArrayList<>();
        for (DiscountEntity discount : discountEntityList) {
            DiscountOutputDTO discountDTO = new DiscountOutputDTO();
            discountDTO.setId(discount.getId());
            discountDTO.setPromotion(discount.getPromotion());
            discountDTO.setQuantity(discount.getQuantity());
            discountDTO.setCategory(discount.getArticle().getCategory().getType());
            discountDTO.setName(discount.getArticle().getName());
            discountDTO.setBrand(discount.getArticle().getBrand());
            discountDTO.setDescription(discount.getArticle().getDescription());
            discountDTO.setImageUrl(discount.getArticle().getImageUrl());
            discountDTO.setPrice(discount.getArticle().getPrice());
            List<StockDTO> stockDTOList = new ArrayList<>();
            for (StockEntity stock : discount.getArticle().getStockList()) {
                StockDTO stockDTO = new StockDTO();
                stockDTO.setSize(stock.getSize());
                stockDTO.setUnits(stock.getUnits());
                stockDTOList.add(stockDTO);
            }
            discountDTO.setStockList(stockDTOList);

            outputList.add(discountDTO);
        }
        return outputList;
    }

    @Override
    public DiscountOutputDTO store(DiscountInputDTO discountInputDTO) {

        if ((discountInputDTO.getPromotion().equals("")) || (discountInputDTO.getPromotion().length() > 255)
                || (discountInputDTO.getQuantity().equals(BigDecimal.ZERO))
        ) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Body invalid");
        }

        Optional<ArticleEntity> optionalArticle = articleRepository.findById(discountInputDTO.getId());

        if (!optionalArticle.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are not article with that id");
        }

        Optional<DiscountEntity> optionalDiscount = discountRepository.findByArticleId(discountInputDTO.getId());

        if (optionalDiscount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Discount exists in system");
        }

        DiscountEntity discountDatabase = new DiscountEntity();
        discountDatabase.setArticle(optionalArticle.get());
        discountDatabase.setPromotion(discountInputDTO.getPromotion());
        discountDatabase.setQuantity(discountInputDTO.getQuantity());
        discountDatabase.setCreationDate(LocalDate.now());
        discountDatabase.setUserCreation("");
        discountDatabase.setModificationDate(LocalDate.of(1, 1, 1));
        discountDatabase.setUserModification("");
        discountRepository.save(discountDatabase);

        DiscountOutputDTO outputDiscount = new DiscountOutputDTO();
        outputDiscount.setId(discountDatabase.getId());
        outputDiscount.setPromotion(discountDatabase.getPromotion());
        outputDiscount.setQuantity(discountDatabase.getQuantity());
        outputDiscount.setCategory(discountDatabase.getArticle().getCategory().getType());
        outputDiscount.setName(discountDatabase.getArticle().getName());
        outputDiscount.setBrand(discountDatabase.getArticle().getBrand());
        outputDiscount.setDescription(discountDatabase.getArticle().getDescription());
        outputDiscount.setImageUrl(discountDatabase.getArticle().getImageUrl());
        outputDiscount.setPrice(discountDatabase.getArticle().getPrice());
        List<StockDTO> stockDTOList = new ArrayList<>();
        for (StockEntity stock : discountDatabase.getArticle().getStockList()) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setSize(stock.getSize());
            stockDTO.setUnits(stock.getUnits());
            stockDTOList.add(stockDTO);
        }
        outputDiscount.setStockList(stockDTOList);

        return outputDiscount;
    }

    @Override
    public void delete(int id) {

        Optional<DiscountEntity> optionalDiscount = discountRepository.findById(id);

        if (!optionalDiscount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discount does not exist");
        }
        discountRepository.delete(optionalDiscount.get());
    }

    @Override
    public DiscountOutputDTO findByArticleId(int articleId) {

        Optional<DiscountEntity> optionalDiscount = discountRepository.findByArticleId(articleId);

        if (!optionalDiscount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Discount does not exist");
        }

        DiscountOutputDTO outputDiscount = new DiscountOutputDTO();
        outputDiscount.setId(optionalDiscount.get().getId());
        outputDiscount.setPromotion(optionalDiscount.get().getPromotion());
        outputDiscount.setQuantity(optionalDiscount.get().getQuantity());
        outputDiscount.setCategory(optionalDiscount.get().getArticle().getCategory().getType());
        outputDiscount.setName(optionalDiscount.get().getArticle().getName());
        outputDiscount.setBrand(optionalDiscount.get().getArticle().getBrand());
        outputDiscount.setDescription(optionalDiscount.get().getArticle().getDescription());
        outputDiscount.setImageUrl(optionalDiscount.get().getArticle().getImageUrl());
        outputDiscount.setPrice(optionalDiscount.get().getArticle().getPrice());
        List<StockDTO> stockDTOList = new ArrayList<>();
        for (StockEntity stock : optionalDiscount.get().getArticle().getStockList()) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setSize(stock.getSize());
            stockDTO.setUnits(stock.getUnits());
            stockDTOList.add(stockDTO);
        }
        outputDiscount.setStockList(stockDTOList);

        return outputDiscount;
    }
}
