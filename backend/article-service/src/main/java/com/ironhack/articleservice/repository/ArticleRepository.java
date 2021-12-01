package com.ironhack.articleservice.repository;

import com.ironhack.articleservice.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    @Query("SELECT a FROM ArticleEntity AS a LEFT JOIN FETCH a.stockList WHERE a.category.type = :category")
    List<ArticleEntity> findByCategoryType(@Param("category") String category);

    @Query("SELECT a FROM ArticleEntity AS a LEFT JOIN FETCH a.stockList WHERE a.name LIKE %:name%")
    List<ArticleEntity> findByNameContainingIgnoreCase(@Param("name") String name);
}
