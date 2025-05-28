package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity @Getter
@Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Seller extends Customer {
    @OneToMany(mappedBy = "seller")
    private List<Sale> sales;

    private String phone;
}
