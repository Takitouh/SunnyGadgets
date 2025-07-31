package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.Set;

public record SellerCreateDTO(
        @Schema(description = "Salary of the seller", example = "4000")
        @NotNull(message = "The salary can't be null") @Positive(message = "The salary can't be negative or zero")
        Long salary,
        @Schema(description = "Name of the seller", example = "Max Holloway")
        @NotBlank(message = "Name can't be blank or null")
        String name,
        @Schema(description = "Phone of the seller", example = "3154125623")
        @Size(max = 10, min = 10, message = "The length of the phone is invalid")
        String phoneNumber,
        @Schema(description = "ID's of sales that seller did", example = "1, 6, 11")
        Set<Long> sales,
        @Schema(description = "Commission of the seller", example = "200")
        @Positive(message = "The commission can't be negative or zero")
        Long commission
) {
}
