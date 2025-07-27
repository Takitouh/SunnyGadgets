package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCustomer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/customer")
public class ControllerCustomer {
    private final IServiceCustomer serviceCustomer;


    public ControllerCustomer(IServiceCustomer serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.getCustomerById(id));

    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(serviceCustomer.allCustomers());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerCreateDTO customer) {
        return new ResponseEntity<>(serviceCustomer.createCustomer(customer), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<CustomerResponseDTO>> createCustomer(@RequestBody List<CustomerCreateDTO> customers) {
        return new ResponseEntity<>(serviceCustomer.createCustomer(customers), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CustomerResponseDTO> deleteCustomer(@PathVariable Long id) {
        serviceCustomer.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerPutDTO customer, @PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.updateCustomer(customer, id));
    }

    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerPatchDTO customer, @PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.updateCustomer(customer, id));
    }

    @GetMapping("/findby-age/{age}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameCustomerDTO>> getAllCustomersByAge(@PathVariable Integer age) {
        return ResponseEntity.ok(serviceCustomer.findCustomersByAgeGreaterThanEqual(age));
    }

    @GetMapping("/find-purchases/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<SaleResponseDTO>> getPurchaseCustomers(@PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.findPurchaseCustomers(id));
    }
}
