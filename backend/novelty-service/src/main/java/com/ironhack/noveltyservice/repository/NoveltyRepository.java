package com.ironhack.noveltyservice.repository;

import com.ironhack.noveltyservice.model.NoveltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoveltyRepository extends JpaRepository<NoveltyEntity, Integer> {

    @Query("SELECT n FROM NoveltyEntity AS n LEFT JOIN FETCH n.article WHERE n.article.id = :articleId")
    Optional<NoveltyEntity> findByArticleId(@Param("articleId") int id);
}
