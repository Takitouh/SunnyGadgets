package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomer implements IServiceCustomer {

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        return null;
    }

    @Override
    public ResponseEntity<List<Customer>> createCustomer(List<Customer> customers) {
        return null;
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Customer>> allCustomers() {
        return null;
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Customer customer, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Customer> deleteCustomer(Long id) {
        return null;
    }
}
