package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceCustomer {
    ResponseEntity<Customer> createCustomer(Customer customer);
    ResponseEntity<List<Customer>> createCustomer(List<Customer> customers);
    ResponseEntity<Customer> getCustomerById(Long id);
    ResponseEntity<List<Customer>> allCustomers();
    ResponseEntity<Customer> updateCustomer(Customer customer, Long id);
    ResponseEntity<Customer> deleteCustomer(Long id);

}
