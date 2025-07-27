package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String name;
    private String description;
    private long price;
    private int stock;
    @ManyToOne
    @JoinColumn(referencedColumnName = "idCategory", nullable = false)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Category category;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "productSet")
    private Set<Provider> setProviders;

}
