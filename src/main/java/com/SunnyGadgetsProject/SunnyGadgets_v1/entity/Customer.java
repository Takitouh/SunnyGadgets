package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
