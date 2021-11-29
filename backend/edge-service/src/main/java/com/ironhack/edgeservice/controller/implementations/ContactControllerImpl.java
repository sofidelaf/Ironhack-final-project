package com.ironhack.edgeservice.controller.implementations;

import com.ironhack.edgeservice.controller.dto.ContactDTO;
import com.ironhack.edgeservice.controller.interfaces.ContactController;
import com.ironhack.edgeservice.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class ContactControllerImpl implements ContactController {

    @Autowired
    private ContactService contactService;

    @Override
    @PostMapping("/contact")
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody ContactDTO contactDTO) {

        contactService.store(contactDTO);
    }
}
