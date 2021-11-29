package com.ironhack.edgeservice.service.implementations;

import com.ironhack.edgeservice.controller.dto.ContactDTO;
import com.ironhack.edgeservice.model.ContactEntity;
import com.ironhack.edgeservice.repository.ContactRepository;
import com.ironhack.edgeservice.service.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void store(ContactDTO contactDTO) {

        if ( (contactDTO.getFullName().equals("")) || (contactDTO.getEmail().equals("")) ||
                (contactDTO.getSubject().equals("")) || (contactDTO.getDetails().equals("")) )   {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid body");
        }
        ContactEntity contact = new ContactEntity();
        contact.setFullName(contactDTO.getFullName());
        contact.setEmail(contactDTO.getEmail());
        contact.setSubject(contactDTO.getSubject());
        contact.setDetails(contactDTO.getDetails());
        contact.setCreationDatetime(Timestamp.valueOf(LocalDateTime.now()));
        contactRepository.save(contact);
    }
}
