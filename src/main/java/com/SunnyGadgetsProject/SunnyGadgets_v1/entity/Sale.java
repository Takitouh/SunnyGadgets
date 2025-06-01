package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_sale;
    @CurrentTimestamp
    private Timestamp salecreatedAt;
    @Min(1)
    private int total;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id_seller", nullable = false)
    private Seller seller;

    @OneToMany
    private List<DetailSale> listdetailSale;

}
