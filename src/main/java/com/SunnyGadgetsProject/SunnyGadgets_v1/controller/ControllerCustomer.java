package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCustomer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return serviceCustomer.getCustomerById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return serviceCustomer.allCustomers();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer userSunnyGadgets) {
        return serviceCustomer.createCustomer(userSunnyGadgets);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Customer>> createCustomer(@RequestBody List<Customer> userSunnyGadgets) {
        return serviceCustomer.createCustomer(userSunnyGadgets);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        return serviceCustomer.deleteCustomer(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer userSunnyGadgets, @PathVariable Long id) {
        return serviceCustomer.updateCustomer(userSunnyGadgets, id);
    }
}
