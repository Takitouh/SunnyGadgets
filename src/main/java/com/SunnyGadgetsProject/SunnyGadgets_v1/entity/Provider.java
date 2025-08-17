package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@AttributeOverride(
        name  = "idEmployee",
        column = @Column(name = "idProvider")
)
@Table(name = "providers")

public class Provider extends Employee {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            @JoinTable(name = "Orders", joinColumns = @JoinColumn(referencedColumnName = "idProvider"),
                    inverseJoinColumns = @JoinColumn(referencedColumnName = "idProduct"))

    private Set<Product> productSet;

    private String company;

    private String email;

}
