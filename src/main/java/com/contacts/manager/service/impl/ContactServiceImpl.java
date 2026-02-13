package com.contacts.manager.service.impl;

import com.contacts.manager.dao.ContactRepository;
import com.contacts.manager.dto.ContactDTO;
import com.contacts.manager.model.Contact;
import com.contacts.manager.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ContactDTO> getAllContacts(Pageable pageable) {
        return contactRepository.findAll(pageable).map(this::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ContactDTO getContactById(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + id));
        return toDTO(contact);
    }

    @Override
    public ContactDTO createContact(ContactDTO dto) {
        Contact contact = toEntity(dto);
        Contact saved = contactRepository.save(contact);
        return toDTO(saved);
    }

    @Override
    public ContactDTO updateContact(UUID id, ContactDTO dto) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + id));

        contact.setName(dto.getName());
        contact.setLastName(dto.getLastName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setAddressLine1(dto.getAddressLine1());
        contact.setAddressLine2(dto.getAddressLine2());
        contact.setCountry(dto.getCountry());
        contact.setState(dto.getState());
        contact.setCity(dto.getCity());
        contact.setBirthday(dto.getBirthday());

        Contact updated = contactRepository.save(contact);
        return toDTO(updated);
    }

    @Override
    public void deleteContact(UUID id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    private ContactDTO toDTO(Contact contact) {
        return ContactDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .lastName(contact.getLastName())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .addressLine1(contact.getAddressLine1())
                .addressLine2(contact.getAddressLine2())
                .country(contact.getCountry())
                .state(contact.getState())
                .city(contact.getCity())
                .birthday(contact.getBirthday())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }

    private Contact toEntity(ContactDTO dto) {
        return Contact.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .country(dto.getCountry())
                .state(dto.getState())
                .city(dto.getCity())
                .birthday(dto.getBirthday())
                .build();
    }
}
