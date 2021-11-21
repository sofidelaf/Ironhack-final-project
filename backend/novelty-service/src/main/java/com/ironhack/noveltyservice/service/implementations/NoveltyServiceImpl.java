package com.ironhack.noveltyservice.service.implementations;

import com.ironhack.noveltyservice.controller.dto.NoveltyOutputDTO;
import com.ironhack.noveltyservice.repository.ArticleRepository;
import com.ironhack.noveltyservice.repository.NoveltyRepository;
import com.ironhack.noveltyservice.service.interfaces.NoveltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoveltyServiceImpl implements NoveltyService {

    @Autowired
    private NoveltyRepository noveltyRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<NoveltyOutputDTO> getAll() {
        return null;
    }

    @Override
    public NoveltyOutputDTO store(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
