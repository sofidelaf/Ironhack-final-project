package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.controller.dto.DiscountInputDTO;
import com.ironhack.edgeservice.controller.dto.DiscountOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("discount-service")
public interface DiscountClient {

    @GetMapping("/discounts")
    List<DiscountOutputDTO> getAllDiscounts();

    @PostMapping("/discounts")
    DiscountOutputDTO storeDiscount(@RequestBody DiscountInputDTO discountInputDTO);

    @DeleteMapping("/discounts/{id}")
    void deleteDiscount(@PathVariable(name = "id") int id);

    @GetMapping("/discounts-by-article-id")
    DiscountOutputDTO findByArticleId(@RequestParam(name = "articleId") int articleId);
}
