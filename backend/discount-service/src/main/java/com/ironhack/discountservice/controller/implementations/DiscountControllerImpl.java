package com.ironhack.discountservice.controller.implementations;

import com.ironhack.discountservice.controller.dto.DiscountInputDTO;
import com.ironhack.discountservice.controller.dto.DiscountOutputDTO;
import com.ironhack.discountservice.controller.interfaces.DiscountController;
import com.ironhack.discountservice.service.interfaces.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiscountControllerImpl implements DiscountController {

    @Autowired
    private DiscountService discountService;

    @Override
    @GetMapping("/discounts")
    @ResponseStatus(HttpStatus.OK)
    public List<DiscountOutputDTO> getAll() {

        return discountService.getAll();
    }

    @Override
    @PostMapping("/discounts")
    @ResponseStatus(HttpStatus.CREATED)
    public DiscountOutputDTO store(@RequestBody DiscountInputDTO discountInputDTO) {

        return discountService.store(discountInputDTO);
    }

    @Override
    @DeleteMapping("/discounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id) {

        discountService.delete(id);
    }

    @Override
    @GetMapping("/discounts-by-article-id")
    @ResponseStatus(HttpStatus.OK)
    public DiscountOutputDTO findByArticleId(@RequestParam(name = "articleId") int articleId) {

        return discountService.findByArticleId(articleId);
    }
}
