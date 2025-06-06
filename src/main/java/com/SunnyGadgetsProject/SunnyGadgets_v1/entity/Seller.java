package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "SELLER")
@PrimaryKeyJoinColumn(name = "id_seller")

public class Seller extends Employee {

    @OneToMany(mappedBy = "seller")
    private List<Sale> sales;
}
