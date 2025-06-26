package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;
    private String name;
    private String description;
    private int price;
    private int stock;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_category", nullable = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;

    @ManyToMany(mappedBy = "productSet")
    //@JsonIgnore
    private Set<Provider> setProviders;

}
