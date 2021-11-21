package com.ironhack.noveltyservice.repository;

import com.ironhack.noveltyservice.model.NoveltyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoveltyRepository extends JpaRepository<NoveltyEntity, Integer> {
}
