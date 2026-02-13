package com.contacts.manager.service;

import com.contacts.manager.dto.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ContactService {

    Page<ContactDTO> getAllContacts(Pageable pageable);

    ContactDTO getContactById(UUID id);

    ContactDTO createContact(ContactDTO contactDTO);

    ContactDTO updateContact(UUID id, ContactDTO contactDTO);

    void deleteContact(UUID id);
}
