package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "detailsales")

public class DetailSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detailsale;
    private int quantity;

    private long unitPrice;

    private long subtotal;

    @ManyToOne
    @JoinColumn(name = "fk_sale", referencedColumnName = "id_sale")
    @JsonIgnore
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "fk_product", referencedColumnName = "id_product")
    private Product product;


}
