package com.SunnyGadgetsProject.SunnyGadgets_v1.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter
@Setter
@NoArgsConstructor
public class Employee extends UserSunnyGadgets{
    private int salary;
}
