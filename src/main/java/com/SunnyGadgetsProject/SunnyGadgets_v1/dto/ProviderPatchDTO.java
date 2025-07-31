package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Set;

public record ProviderPatchDTO(
        @Schema(description = "Salary of the provider", example = "3500")
        @Positive(message = "The salary can't be negative or zero")
        Long salary,

        @Schema(description = "Name of provider", example = "Alexander Volkanovski")
        String name,

        @Schema(description = "Phone of provider", example = "3124152452")
        @Size(max = 10, min = 10, message = "The length of the phone is invalid")
        String phoneNumber,

        @Schema(description = "Optional ID's of existent products", example = "1")
        Set<Long> existentProductsIds,

        @Schema(description = "Name of company of the provider", example = "AMD")
        String company,

        @Schema(description = "Email of the provider", example = "alexvolkanovski@gmail.com")
        @Email(message = "Invalid email")
        String email
) {
}
