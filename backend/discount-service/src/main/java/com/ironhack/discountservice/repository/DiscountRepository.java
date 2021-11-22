package com.ironhack.discountservice.repository;

import com.ironhack.discountservice.model.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Integer> {

    @Query("SELECT d FROM DiscountEntity AS d LEFT JOIN FETCH d.article WHERE d.article.id = :articleId")
    Optional<DiscountEntity> findByArticleId(@Param("articleId") int id);
}
