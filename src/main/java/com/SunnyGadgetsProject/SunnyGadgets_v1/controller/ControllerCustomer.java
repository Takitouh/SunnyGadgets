package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCustomer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/customer")
public class ControllerCustomer {
    private final IServiceCustomer serviceCustomer;

    public ControllerCustomer(IServiceCustomer serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customerOptional = serviceCustomer.getCustomerById(id);
        if (customerOptional.isEmpty()){
            throw new EntityNotFoundException("Customer with ID " + id + " not found");
        }
        return ResponseEntity.ok(customerOptional.get());
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = serviceCustomer.allCustomers();
        if (customers.isEmpty()){
            throw new EntityNotFoundException("Customer list is empty");
        }
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        serviceCustomer.createCustomer(customer);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Customer>> createCustomer(@RequestBody List<Customer> customers) {
        serviceCustomer.createCustomer(customers);

        return new ResponseEntity<>(customers, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        serviceCustomer.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.updateCustomer(customer, id));
    }
}
