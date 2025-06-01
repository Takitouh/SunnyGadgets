package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "id_seller"))

public class Seller extends UserSunnyGadgets {
    @OneToMany(mappedBy = "seller")
    private List<Sale> sales;

    private String phone;
}
