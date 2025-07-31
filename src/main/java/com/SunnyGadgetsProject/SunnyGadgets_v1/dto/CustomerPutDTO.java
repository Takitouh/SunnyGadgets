package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

public record CustomerPutDTO(
    @Schema(description = "Age of the customer", example = "18")
    @Min(value = 16, message = "The minimum age is 16")
    int age,

    @Schema(description = "Address of the customer", example = "1 Maple St, New York Mills, NY 13417, United States")
    @NotBlank(message = "Address can't be blank or null")
    String address,

    @Schema(description = "Name of customer", example = "Bruce Willis")
    @NotBlank(message = "Name can't be blank or null")
    String name,

    @Schema(description = "Email of customer", example = "brucewillis@gmail.com")
    @Email(message = "Invalid email")
    String email,

    @Schema(description = "Sales associated with the customer")
    @Valid
    Set<SaleCreateDTO> sales,

    @Schema(description = "Phone of customer", example = "31045126289")
    @Size(max = 10, min = 10, message = "The length of the phone is invalid")
    String phoneNumber
) {
    public CustomerPutDTO {
        if (sales == null) {
            sales = new HashSet<>();
        }
    }
}
