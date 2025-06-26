package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customers")

public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_customer;

  private int age;
  private String address;
  private String name;
  private String email;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
  private Set<Sale> sales;

  private String phone;
  private Timestamp creationDate;
  private Timestamp modificationDate;

}
