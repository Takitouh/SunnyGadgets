package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_seller"))

public class Seller extends Employee {
    @OneToMany(mappedBy = "seller")
    private List<Sale> sales;

    @Size(max = 10, min = 10) @NotBlank
    @Nonnull
    private String phone;

}
