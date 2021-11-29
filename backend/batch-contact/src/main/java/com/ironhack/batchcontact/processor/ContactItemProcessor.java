package com.ironhack.batchcontact.processor;

import com.ironhack.batchcontact.model.Contact;
import org.springframework.batch.item.ItemProcessor;

public class ContactItemProcessor implements ItemProcessor<Contact, Contact> {

    @Override
    public Contact process(Contact contact) throws Exception {

        return contact;
    }
}
