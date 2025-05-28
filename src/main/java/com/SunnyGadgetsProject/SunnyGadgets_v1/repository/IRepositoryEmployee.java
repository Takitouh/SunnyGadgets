package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryEmployee extends JpaRepository<Customer, Long> {
}
