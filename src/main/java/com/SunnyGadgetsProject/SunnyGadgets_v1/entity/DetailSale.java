package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class DetailSale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_detailsale;
    @Min(1)
    private int quantity;
    @Min(1)
    private long unitPrice;

    //PD: Debemos mejorar el modelo de entidades

}
