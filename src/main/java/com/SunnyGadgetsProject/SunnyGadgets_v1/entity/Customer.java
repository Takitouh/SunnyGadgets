package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Customer extends UserSunnyGadgets{

  @NotBlank @Column(name = "Address", nullable = false)
    private String address;

  @Min(10) @Max(10) @NotBlank @Column(name = "phoneNumber", nullable = false)
    private String phone;

}
