package com.ironhack.articleservice.controller.dto;

import com.ironhack.articleservice.enums.ArticleSize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ArticleDTO {

    private int id;
    private String name;
    private String category;
    private String brand;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ArticleSize size;
    private short units;

    private LocalDate creationDate;
    private String userCreation;
    private LocalDate modificationDate;
    private String userModification;
}
