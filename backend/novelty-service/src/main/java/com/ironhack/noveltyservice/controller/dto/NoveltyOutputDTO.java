package com.ironhack.noveltyservice.controller.dto;

import com.ironhack.noveltyservice.enums.StockDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<StockDTO> stockList;
}
