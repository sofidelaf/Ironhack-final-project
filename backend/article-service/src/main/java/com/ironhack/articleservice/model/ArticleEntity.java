package com.ironhack.articleservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_type")
    private CategoryEntity category;

    private String name;
    private String brand;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "article")
    private List<StockEntity> stockList;

    private LocalDate creationDate;
    private String userCreation;
    private LocalDate modificationDate;
    private String userModification;
}
