package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSeller;
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
@RequestMapping("/api/v1/seller")
public class ControllerSeller {
    private final IServiceSeller serviceSeller;

    public ControllerSeller(IServiceSeller serviceSeller) {
        this.serviceSeller = serviceSeller;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Seller> getSeller(@PathVariable Long id) {
        Optional<Seller> sellerOptional = serviceSeller.getSellerById(id);
        if (sellerOptional.isEmpty()){
            throw new EntityNotFoundException("Seller with ID " + id + " not found");
        }
        return ResponseEntity.ok(sellerOptional.get());
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = serviceSeller.allSellers();
        if (sellers.isEmpty()){
            throw new EntityNotFoundException("Seller list is empty");
        }
        return ResponseEntity.ok(sellers);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        serviceSeller.createSeller(seller);

        return new ResponseEntity<>(seller, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Seller>> createSellers(@RequestBody List<Seller> sellers) {
        serviceSeller.createSeller(sellers);
        return new ResponseEntity<>(sellers, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Seller> deleteSeller(@PathVariable Long id) {
        serviceSeller.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller, @PathVariable Long id) {
        serviceSeller.updateSeller(seller, id);
        return ResponseEntity.ok(seller);
    }
}

