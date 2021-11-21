package com.ironhack.noveltyservice.repository;

import com.ironhack.noveltyservice.model.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
}
