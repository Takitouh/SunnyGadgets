package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_customer")
  private Long id;

  @NotBlank @Nonnull @Column(name = "Name")
  private String name;

  @NotBlank  @Min(16) @Column(name = "Age")
  private int age;

  @Column(name = "Email", unique = true, nullable = true)
  @Email
  private String email;

  @CreationTimestamp
  private Timestamp creationDate;

  @UpdateTimestamp
  private Timestamp modificationDate;

  @NotBlank @Column(name = "Address", nullable = false)
    private String address;

  @Min(10) @Max(10) @NotBlank @Column(name = "Phone", nullable = false)
    private String phone;

}
