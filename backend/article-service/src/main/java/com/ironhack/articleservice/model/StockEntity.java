package com.ironhack.articleservice.model;

import com.ironhack.articleservice.enums.ArticleSize;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "stock")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    @Enumerated(EnumType.STRING)
    private ArticleSize size;
    private short units;
    private LocalDate creationDate;
    private String userCreation;
    private LocalDate modificationDate;
    private String userModification;

    public int getId() {
        return id;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public ArticleSize getSize() {
        return size;
    }

    public void setSize(ArticleSize size) {
        this.size = size;
    }

    public short getUnits() {
        return units;
    }

    public void setUnits(short units) {
        this.units = units;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setUserModification(String userModification) {
        this.userModification = userModification;
    }
}
