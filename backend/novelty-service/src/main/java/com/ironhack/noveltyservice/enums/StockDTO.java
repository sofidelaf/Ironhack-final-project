package com.ironhack.noveltyservice.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class StockDTO {

    private String size;
    private short units;
    private BigDecimal price;
}
