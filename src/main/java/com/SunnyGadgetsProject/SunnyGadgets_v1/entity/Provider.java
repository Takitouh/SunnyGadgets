package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "PROVIDER")
@PrimaryKeyJoinColumn(name = "id_provider")
@Table(name = "providers")

public class Provider extends Employee {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            @JoinTable(name = "Orders", joinColumns = @JoinColumn(referencedColumnName = "id_provider"),
                    inverseJoinColumns = @JoinColumn(referencedColumnName = "id_product"))

    private Set<Product> productSet;


}
