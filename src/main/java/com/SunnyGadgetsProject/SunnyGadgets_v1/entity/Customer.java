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
    @Min(16) @Column(name = "Age", nullable = false)
  private int age;

  @NotBlank @Column(name = "Address", nullable = false)
    private String address;

  @NotBlank
  @Nonnull
  private String name;
  @Column(unique = true)
  @Email
  private String email;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
  @JsonIgnore
  private Set<Sale> sales;

  @Size(max = 10, min = 10) @NotBlank @Nonnull
  private String phone;
  @CreationTimestamp
  private Timestamp creationDate;
  @UpdateTimestamp
  private Timestamp modificationDate;

}
