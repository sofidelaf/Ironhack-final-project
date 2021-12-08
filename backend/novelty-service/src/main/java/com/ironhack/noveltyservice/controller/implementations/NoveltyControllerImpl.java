package com.ironhack.noveltyservice.controller.implementations;

import com.ironhack.noveltyservice.controller.dto.NoveltyInputDTO;
import com.ironhack.noveltyservice.controller.dto.NoveltyOutputDTO;
import com.ironhack.noveltyservice.controller.interfaces.NoveltyController;
import com.ironhack.noveltyservice.service.interfaces.NoveltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoveltyControllerImpl implements NoveltyController {

    @Autowired
    private NoveltyService noveltyService;

    @Override
    @GetMapping("/novelties")
    @ResponseStatus(HttpStatus.OK)
    public List<NoveltyOutputDTO> getAll() {

        return noveltyService.getAll();
    }

    @Override
    @PostMapping("/novelties")
    @ResponseStatus(HttpStatus.CREATED)
    public NoveltyOutputDTO store(@RequestBody NoveltyInputDTO noveltyInputDTO) {

        return noveltyService.store(noveltyInputDTO.getId());
    }

    @Override
    @DeleteMapping("/novelties/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id) {

        noveltyService.delete(id);
    }

    @Override
    @GetMapping("/novelties-by-article-id")
    @ResponseStatus(HttpStatus.OK)
    public NoveltyOutputDTO findByArticleId(@RequestParam(name = "articleId") int articleId) {

        return noveltyService.findByArticleId(articleId);
    }
}
