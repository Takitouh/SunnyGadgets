package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    @Nonnull @NotBlank(message = "The name of the product can't be blank or null")
    private String name;

    private String description;
    @Positive(message = "The price can't be negative or zero")
    private long price;
    @PositiveOrZero(message = "The stock can't be negative")
    private int stock;
    @NotNull(message = "The product must have at least one category")
    private Long  idCategory;

    private Set<Long> existingProvidersIds = new HashSet<>();


    /*
     In the service we will check if at least one provider was sent
     */
}
