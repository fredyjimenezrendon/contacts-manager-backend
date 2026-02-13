package com.contacts.manager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Contact data transfer object")
public class ContactDTO {

    @Schema(description = "Contact ID", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Schema(description = "First name", example = "John")
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    @Schema(description = "Last name", example = "Doe")
    private String lastName;

    @Size(max = 20, message = "Phone must not exceed 20 characters")
    @Pattern(regexp = "^$|^\\+?[0-9\\-\\s()]{7,20}$", message = "Phone number format is invalid")
    @Schema(description = "Phone number", example = "+1234567890")
    private String phone;

    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;

    @Size(max = 255, message = "Address line 1 must not exceed 255 characters")
    @Schema(description = "Address line 1", example = "123 Main St")
    private String addressLine1;

    @Size(max = 255, message = "Address line 2 must not exceed 255 characters")
    @Schema(description = "Address line 2", example = "Apt 4B")
    private String addressLine2;

    @Size(max = 100, message = "Country must not exceed 100 characters")
    @Schema(description = "Country", example = "United States")
    private String country;

    @Size(max = 100, message = "State must not exceed 100 characters")
    @Schema(description = "State", example = "California")
    private String state;

    @Size(max = 100, message = "City must not exceed 100 characters")
    @Schema(description = "City", example = "Los Angeles")
    private String city;

    @Past(message = "Birthday must be in the past")
    @Schema(description = "Birthday", example = "1990-05-15")
    private LocalDate birthday;

    @Schema(description = "Creation timestamp", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
}
