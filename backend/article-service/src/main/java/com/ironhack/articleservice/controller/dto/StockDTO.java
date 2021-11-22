package com.ironhack.articleservice.controller.dto;

import com.ironhack.articleservice.enums.ArticleSize;
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
