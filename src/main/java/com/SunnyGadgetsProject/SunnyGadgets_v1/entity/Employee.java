package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor

public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_employee;

    @NotBlank @Nonnull
    private Long salary;

    @NotBlank
    @Nonnull
    private String name;
    @Column(unique = true)
    @Email
    private String email;

    @Size(max = 10, min = 10) @NotBlank @Nonnull
    private String phone;
    @CreationTimestamp
    private Timestamp creationDate;
    @UpdateTimestamp
    private Timestamp modificationDate;

}
