package com.ironhack.edgeservice.controller.implementations;

import com.ironhack.edgeservice.controller.dto.*;
import com.ironhack.edgeservice.controller.interfaces.EdgeController;
import com.ironhack.edgeservice.service.interfaces.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class EdgeControllerImpl implements EdgeController {

    @Autowired
    private EdgeService edgeService;

    @Override
    @PostMapping("/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO storeArticle(@RequestBody ArticleDTO articleDTO) {

        return edgeService.storeArticle(articleDTO);
    }

    @Override
    @GetMapping("/articles")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleOutputDTO> getAllArticles(@RequestParam(name = "category") Optional<String> category) {

        if (category.isPresent()) {
            return edgeService.getAllArticlesByCategory(category.get());
        } else {
            return edgeService.getAllArticles();
        }
    }

    @Override
    @GetMapping("/articles-by-name")
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleOutputDTO> getByNameLike(@RequestParam("name") String name) {

        return edgeService.getByNameLike(name);
    }

    @Override
    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable(name = "id") int id) {

        edgeService.deleteArticle(id);
    }

    @Override
    @PatchMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(@PathVariable(name = "id") int id, @RequestBody ArticleDTO articleDTO) {

        edgeService.updatePrice(id, articleDTO);
    }

    @Override
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getAllCategories() {

        return edgeService.getAllCategories();
    }

    @Override
    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO storeCategory(@RequestBody CategoryDTO categoryDTO) {

        return edgeService.storeCategory(categoryDTO);
    }

    @Override
    @GetMapping("/discounts")
    @ResponseStatus(HttpStatus.OK)
    public List<DiscountOutputDTO> getAllDiscounts() {

        return edgeService.getAllDiscounts();
    }

    @Override
    @PostMapping("/discounts")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountOutputDTO storeDiscount(@RequestBody DiscountInputDTO discountInputDTO) {

        return edgeService.storeDiscount(discountInputDTO);
    }

    @Override
    @DeleteMapping("/discounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDiscount(@PathVariable(name = "id") int id) {

        edgeService.deleteDiscount(id);
    }

    @Override
    @GetMapping("/novelties")
    @ResponseStatus(HttpStatus.OK)
    public List<NoveltyOutputDTO> getAllNovelties() {

        return edgeService.getAllNovelties();
    }

    @Override
    @PostMapping("/novelties")
    @ResponseStatus(HttpStatus.CREATED)
    public NoveltyOutputDTO storeNovelty(@RequestBody NoveltyInputDTO noveltyInputDTO) {

        return edgeService.storeNovelty(noveltyInputDTO);
    }

    @Override
    @DeleteMapping("/novelties/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNovelty(@PathVariable(name = "id") int id) {

        edgeService.deleteNovelty(id);
    }
}
