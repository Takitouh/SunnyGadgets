package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "categorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_category;
    @NotBlank @Nonnull
    private String name;
    @NotBlank @Nonnull
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    Set<Product> productSet;
}
