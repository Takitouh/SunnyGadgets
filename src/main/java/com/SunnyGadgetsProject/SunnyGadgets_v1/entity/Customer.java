package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
  private Long idCustomer;

  private int age;
  private String address;
  private String name;
  private String email;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
  private Set<Sale> purchases;

  private String phoneNumber;

  @CreationTimestamp
  private Timestamp createdAt;
  @UpdateTimestamp
  private Timestamp updatedAt;

}
