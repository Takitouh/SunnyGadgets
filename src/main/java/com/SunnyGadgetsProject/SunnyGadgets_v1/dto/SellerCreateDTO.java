package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SellerCreateDTO {
    @NotNull @Positive(message = "The salary can't be negative or zero")
    private Long salary;

    @NotBlank(message = "Name can't be blank or null") @NotNull
    private String name;

    @Size(max = 10, min = 10, message = "The length of the phone is invalid")
    private String phone;
    @CreationTimestamp
    private Timestamp creationDate;
    @UpdateTimestamp
    private Timestamp modificationDate;

    @Valid
    private Set<SaleCreateDTO> sales;
    @Positive(message = "The commision can't be negative or zero")
    private Long commision;
    @NotBlank(message = "Hire date can't be blank or null") @NotNull
    private Date hireDate;
}
