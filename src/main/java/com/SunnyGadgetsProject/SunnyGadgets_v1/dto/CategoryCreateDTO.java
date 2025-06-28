package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CategoryCreateDTO {

    private Long idCategory;
    @NotBlank(message = "Name can't be blank or null") @Nonnull
    private String name;

    private String description;
    //Field for id reference to a existing product
    private Set<@NotNull Long> existingProductIds = new HashSet<>();
    //Field for a new product
    @Valid
    private Set<ProductCreateDTO> newProducts = new HashSet<>();

    /*
     In the service we will check if at least one product was sent
     */
}
