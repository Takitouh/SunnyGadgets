package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity @Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = "SELLER")
@PrimaryKeyJoinColumn(name = "id_seller")
@Table(name = "sellers")

public class Seller extends Employee {

    @OneToMany(mappedBy = "seller")
    private Set<Sale> sales;

    private long commision;

    private Date hireDate;
}
