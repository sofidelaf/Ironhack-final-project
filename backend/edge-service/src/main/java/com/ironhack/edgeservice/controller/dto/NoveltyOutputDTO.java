package com.ironhack.edgeservice.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NoveltyOutputDTO {

    private int id;
    private String category;
    private String name;
    private String brand;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private List<StockDTO> stockList;
}
