package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameCustomerDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepositoryCustomer extends JpaRepository<Customer, Long> {
    //Query's
    List<NameCustomerDTO> findCustomersByAgeGreaterThanEqual(Integer age);
}
