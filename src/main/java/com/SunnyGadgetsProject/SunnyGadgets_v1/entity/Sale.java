package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "sales")

public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSale;
    @CurrentTimestamp
    private Timestamp salecreatedAt;

    private long total;

    @ManyToOne
    @JoinColumn(name = "fkCustomer", referencedColumnName = "idCustomer", nullable = false)

    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "fkSeller", referencedColumnName = "idSeller", nullable = false)
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
    private List<DetailSale> listdetailSale;

}
