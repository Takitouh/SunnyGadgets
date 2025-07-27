package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSeller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/seller")
public class ControllerSeller {
    private final IServiceSeller serviceSeller;


    public ControllerSeller(IServiceSeller serviceSeller) {
        this.serviceSeller = serviceSeller;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<SellerResponseDTO> getSeller(@PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.getSellerById(id));
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<SellerResponseDTO>> getAllSellers() {
        return ResponseEntity.ok(serviceSeller.allSellers());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<SellerResponseDTO> createSeller(@RequestBody SellerCreateDTO seller) {
        return new ResponseEntity<>(serviceSeller.createSeller(seller), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<SellerResponseDTO>> createSellers(@RequestBody List<SellerCreateDTO> sellers) {
        return new ResponseEntity<>(serviceSeller.createSeller(sellers), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Seller> deleteSeller(@PathVariable Long id) {
        serviceSeller.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SellerResponseDTO> updateSeller(@RequestBody SellerPutDTO seller, @PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.updateSeller(seller, id));
    }

    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SellerResponseDTO> updateSeller(@RequestBody SellerPatchDTO seller, @PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.updateSeller(seller, id));
    }

    @GetMapping("/get/salaries")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameTotalSalarySeller>> getAllTotalSalarySellers() {
        return ResponseEntity.ok(serviceSeller.getSellersTotalSalary());
    }

    @GetMapping("/find-sales/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<SaleResponseDTO>> getSalesSeller(@PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.findSalesSeller(id));
    }
}

