package com.ironhack.discountservice.controller.interfaces;

import com.ironhack.discountservice.controller.dto.DiscountInputDTO;
import com.ironhack.discountservice.controller.dto.DiscountOutputDTO;

import java.util.List;

public interface DiscountController {

    List<DiscountOutputDTO> getAll();
    DiscountOutputDTO store(DiscountInputDTO discountInputDTO);
    void delete(int id);
    DiscountOutputDTO findByArticleId(int articleId);
}
