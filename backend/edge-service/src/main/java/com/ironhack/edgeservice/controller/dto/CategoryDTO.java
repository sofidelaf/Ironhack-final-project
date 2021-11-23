package com.ironhack.edgeservice.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private String type;
    private String description;
    private LocalDate creationDate;
    private String userCreation;
    private LocalDate modificationDate;
    private String userModification;
}
