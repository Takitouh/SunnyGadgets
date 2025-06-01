package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_provider"))
public class Provider extends UserSunnyGadgets {
    @Size(max = 10, min = 10) @NotBlank @Nonnull
    private String phone;

    @ManyToMany
            @JoinTable(name = "Orders", joinColumns = @JoinColumn(referencedColumnName = "id_provider"),
                    inverseJoinColumns = @JoinColumn(referencedColumnName = "id_product"))

    Set<Product> productSet;


}
