package com.ironhack.articleservice.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ArticleOutputDTO {

    private int id;
    private String name;
    private String category;
    private String brand;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private List<StockDTO> stockList;

    private LocalDate creationDate;
    private String userCreation;
    private LocalDate modificationDate;
    private String userModification;
}
