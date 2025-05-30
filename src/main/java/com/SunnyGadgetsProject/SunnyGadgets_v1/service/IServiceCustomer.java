package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceCustomer {
    ResponseEntity<Customer> createCustomer(Customer userSunnyGadgets);
    ResponseEntity<List<Customer>> createCustomer(List<Customer> userSunnyGadgetsCustomer);
    ResponseEntity<Customer> getCustomerById(Long id);
    ResponseEntity<List<Customer>> allCustomers();
    ResponseEntity<Customer> updateCustomer(Customer userSunnyGadgetsCustomer, Long id);
    ResponseEntity<Customer> deleteCustomer(Long id);

}
