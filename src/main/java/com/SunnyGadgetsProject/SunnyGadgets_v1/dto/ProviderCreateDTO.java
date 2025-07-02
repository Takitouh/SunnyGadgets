package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProviderCreateDTO {
    @NotNull @Positive(message = "The salary can't be negative or zero")
    private Long salary;

    @NotBlank(message = "Name can't be blank or null") @NotNull
    private String name;

    @Size(max = 10, min = 10, message = "The length of the phone is invalid")
    private String phoneNumber;

    private Set<Long> existentProductsIds = new HashSet<>();
//    @Valid
//    private Set<ProductCreateDTO> newProducts = new HashSet<>();
    /*
     In the service we will check if at least one product was sent
     */

    @NotBlank(message = "Company name can't be blank or null") @NotNull
    private String company;
    @Email
    private String email;
}
