package com.contacts.manager.controller;

import com.contacts.manager.dto.ContactDTO;
import com.contacts.manager.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
@Validated
@Tag(name = "Contacts", description = "Contacts management API")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    @Operation(summary = "Get all contacts", description = "Returns a paginated list of contacts sorted by name and last name")
    public ResponseEntity<Page<ContactDTO>> getAllContacts(
            @Parameter(description = "Page number (0-based)") @RequestParam(defaultValue = "0") @Min(0) int page,
            @Parameter(description = "Page size (1-100)") @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name", "lastName").ascending());
        Page<ContactDTO> contacts = contactService.getAllContacts(pageable);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contact by ID", description = "Returns a single contact by its ID")
    public ResponseEntity<ContactDTO> getContactById(
            @Parameter(description = "Contact ID") @PathVariable UUID id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new contact", description = "Creates a new contact and returns the created entity")
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO contactDTO) {
        ContactDTO created = contactService.createContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a contact", description = "Updates an existing contact by its ID")
    public ResponseEntity<ContactDTO> updateContact(
            @Parameter(description = "Contact ID") @PathVariable UUID id,
            @Valid @RequestBody ContactDTO contactDTO) {
        return ResponseEntity.ok(contactService.updateContact(id, contactDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contact", description = "Deletes a contact by its ID")
    public ResponseEntity<Void> deleteContact(
            @Parameter(description = "Contact ID") @PathVariable UUID id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
