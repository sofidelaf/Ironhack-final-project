package com.ironhack.noveltyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal price;

    @OneToMany(mappedBy = "article")
    private List<StockEntity> stockList;

}
