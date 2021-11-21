package com.ironhack.noveltyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "novelty")
public class NoveltyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    private LocalDate creationDate;
    private String userCreation;
    private LocalDate modificationDate;
    private String userModification;
}
