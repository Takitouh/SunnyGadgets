package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.*;

import java.util.Set;

public record SellerCreateDTO(
    @NotNull(message = "The salary can't be null") @Positive(message = "The salary can't be negative or zero")
    Long salary,

    @NotBlank(message = "Name can't be blank or null")
    String name,

    @Size(max = 10, min = 10, message = "The length of the phone is invalid")
    String phoneNumber,

    Set<Long> sales,

    @Positive(message = "The commission can't be negative or zero")
    Long commission
) {}
