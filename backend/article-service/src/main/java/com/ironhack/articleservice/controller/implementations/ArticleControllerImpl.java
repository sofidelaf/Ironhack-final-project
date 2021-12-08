package com.ironhack.articleservice.controller.implementations;

import com.ironhack.articleservice.controller.dto.ArticleDTO;
import com.ironhack.articleservice.controller.dto.ArticleOutputDTO;
import com.ironhack.articleservice.controller.interfaces.ArticleController;
import com.ironhack.articleservice.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleControllerImpl implements ArticleController {

    @Autowired
    private ArticleService articleService;

    @Override
    @PostMapping("/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO store(@RequestBody ArticleDTO articleDTO) {

        return articleService.store(articleDTO);
    }

    @Override
    @GetMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleOutputDTO> getAll(@RequestParam(name = "category") Optional<String> category) {

        if (category.isPresent()) {
            return articleService.getAllByCategory(category.get());
        } else {
            return articleService.getAll();
        }
    }

    @Override
    @GetMapping("/articles-by-name")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleOutputDTO> getByNameLike(@RequestParam("name") String name) {

        return articleService.getByNameLike(name);
    }

    @Override
    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id) {

        articleService.delete(id);
    }

    @Override
    @PatchMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(@PathVariable(name = "id") int id, @RequestBody ArticleDTO articleDTO) {

        articleService.updatePrice(id, articleDTO);
    }
}
