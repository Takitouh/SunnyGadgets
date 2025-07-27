package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

public record CustomerPatchDTO(
        @Min(value = 16, message = "The minimum age is 16")
        Integer age,

        @NotBlank(message = "Address can't be blank or null")
        String address,

        @NotBlank(message = "Name can't be blank or null")
        String name,

        @Email(message = "Invalid email")
        String email,

        @Valid
        Set<SaleCreateDTO> sales,

        @Size(max = 10, min = 10, message = "The length of the phone is invalid")
        String phoneNumber
) {
    public CustomerPatchDTO {
        if (sales == null) {
            sales = new HashSet<>();
        }
    }
}