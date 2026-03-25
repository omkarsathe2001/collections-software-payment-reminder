package com.collections.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    @Size(max = 200, message = "Address too long")
    private String address;
}