package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SellerCreateDTO {
    @NotNull @Positive(message = "The salary can't be negative or zero")
    private Long salary;

    @NotBlank(message = "Name can't be blank") @NotNull(message = "Name can't be null")
    private String name;

    @Size(max = 10, min = 10, message = "The length of the phone is invalid")
    private String phoneNumber;

    @Valid
    private Set<Long> sales;

    @Positive(message = "The commission can't be negative or zero")
    private Long commission;

}
