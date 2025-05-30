package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter @ToString @NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nonnull @NotBlank
    private String name;
    @Nonnull @NotBlank
    private String description;
    @Min(1)
    private int Price;
    @Min(0)
    private int stock;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_category", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "productSet")
    Set<Provider> setProviders;

}
