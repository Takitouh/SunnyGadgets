package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.*;

import java.util.Set;

public record ProviderPatchDTO(
        @Positive(message = "The salary can't be negative or zero")
        Long salary,

        String name,

        @Size(max = 10, min = 10, message = "The length of the phone is invalid")
        String phoneNumber,

        Set<Long> existentProductsIds,

        String company,

        @Email(message = "Invalid email")
        String email
) {
}