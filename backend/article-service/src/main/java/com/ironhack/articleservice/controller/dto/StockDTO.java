package com.ironhack.articleservice.controller.dto;

import com.ironhack.articleservice.enums.ArticleSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockDTO {

    @Enumerated(EnumType.STRING)
    private ArticleSize size;
    private short units;
    private BigDecimal price;
}
