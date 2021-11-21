package com.ironhack.articleservice.repository;

import com.ironhack.articleservice.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    @Query("SELECT c FROM CategoryEntity AS c LEFT JOIN FETCH c.articleList WHERE c.type = :id")
    Optional<CategoryEntity> findById(@Param("id") String id);
}
