package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
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
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateDTO {
    @Min(value = 16, message = "The minimum age is 16")
    private int age;

    @NotBlank(message = "Address can't be blank or null") @Nonnull
    private String address;

    @NotBlank(message = "Name can't be blank or null") @Nonnull
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @Valid
    //WARNING: With valid this could throw an exception when a customer is created for fist time because the sales would be empty
    private Set<SaleCreateDTO> sales = new HashSet<>();

    @Size(max = 10, min = 10, message = "The length of the phone is invalid")
    private String phone;
    @CreationTimestamp
    private Timestamp creationDate;
    @UpdateTimestamp
    private Timestamp modificationDate;
}
