package com.ironhack.edgeservice.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class DiscountInputDTO {

    private int id;
    private String promotion;
    private BigDecimal quantity;
}
