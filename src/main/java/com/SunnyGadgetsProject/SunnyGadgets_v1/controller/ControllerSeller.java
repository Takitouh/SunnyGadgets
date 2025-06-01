package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSeller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/seller")
public class ControllerSeller {
    private final IServiceSeller serviceSeller;

    public ControllerSeller(IServiceSeller serviceSeller) {
        this.serviceSeller = serviceSeller;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Seller> getSeller(@PathVariable Long id) {
        return serviceSeller.getSellerById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Seller>> getAllSellers() {
        return serviceSeller.allSellers();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        return serviceSeller.createSeller(seller);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Seller>> createSellers(@RequestBody List<Seller> sellers) {
        return serviceSeller.createSeller(sellers);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Seller> deleteSeller(@PathVariable Long id) {
        return serviceSeller.deleteSeller(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller, @PathVariable Long id) {
        return serviceSeller.updateSeller(seller, id);
    }
}

