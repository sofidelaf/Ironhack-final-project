package com.ironhack.edgeservice.controller.dto;

import com.ironhack.edgeservice.enums.ArticleSize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Getter
@Setter
public class StockDTO {

    @Enumerated(EnumType.STRING)
    private ArticleSize size;
    private short units;

}
