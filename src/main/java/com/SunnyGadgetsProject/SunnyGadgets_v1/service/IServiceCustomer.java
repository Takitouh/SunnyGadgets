package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface IServiceCustomer {
    Customer createCustomer(Customer userSunnyGadgets);
    List<Customer> createCustomer(List<Customer> userSunnyGadgetsCustomer);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> allCustomers();
    Customer updateCustomer(Customer userSunnyGadgetsCustomer, Long id);
    void deleteCustomer(Long id);

}
