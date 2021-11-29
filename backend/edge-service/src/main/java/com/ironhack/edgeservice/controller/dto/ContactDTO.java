package com.ironhack.edgeservice.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContactDTO {

    private String fullName;
    private String email;
    private String subject;
    private String details;
}
