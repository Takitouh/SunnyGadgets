package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @ToString
@Table(name = "detailsales")
public class DetailSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailSale;
    private Integer quantity;

    private long unitPrice;

    private long subtotal;

    @ManyToOne
    @JoinColumn(name = "fkSale", referencedColumnName = "idSale")
    @JsonIgnore
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "fkProduct", referencedColumnName = "idProduct")
    private Product product;


}
