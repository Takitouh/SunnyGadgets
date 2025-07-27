package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

public record ProviderCreateDTO(
    @NotNull(message = "Salary can't be null") @Positive(message = "The salary can't be negative or zero")
    Long salary,

    @NotBlank(message = "Name can't be blank or null")
    String name,

    @Size(max = 10, min = 10, message = "The length of the phone is invalid")
    String phoneNumber,

    Set<Long> existentProductsIds,


    @NotBlank(message = "Company name can't be blank or null")
    String company,

    @Email(message = "Invalid email")
    String email
) {
    public ProviderCreateDTO {
        if (existentProductsIds == null) {
            existentProductsIds = new HashSet<>();
        }
    }
}
