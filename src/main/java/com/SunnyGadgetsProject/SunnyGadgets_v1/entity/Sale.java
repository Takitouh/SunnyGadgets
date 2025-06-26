package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
@Table(name = "sales")

public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sale;

    private Timestamp salecreatedAt;

    private long total;

    @ManyToOne
    @JoinColumn(name = "fk_customer", referencedColumnName = "id_customer", nullable = false)

    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "fk_seller", referencedColumnName = "id_seller", nullable = false)
    private Seller seller;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<DetailSale> listdetailSale;

}
