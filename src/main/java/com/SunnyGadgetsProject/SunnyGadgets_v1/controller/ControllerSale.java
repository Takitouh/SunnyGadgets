package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSale;
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
@RequestMapping("/api/v1/sale")
public class ControllerSale {
    private final IServiceSale serviceSale;

    public ControllerSale(IServiceSale serviceSale) {
        this.serviceSale = serviceSale;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Sale> getSale(@PathVariable Long id) {
        Optional<Sale> saleOptional = serviceSale.getSaleById(id);
        if (saleOptional.isEmpty()){
            throw new EntityNotFoundException("Sale with ID " + id + " not found");
        }
        return ResponseEntity.ok(saleOptional.get());
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = serviceSale.allSales();
        if (sales.isEmpty()){
            throw new EntityNotFoundException("Sale list is empty");
        }
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        serviceSale.createSale(sale);

        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Sale>> createSales(@RequestBody List<Sale> sales) {
        serviceSale.createSale(sales);

        return new ResponseEntity<>(sales, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Sale> deleteSale(@PathVariable Long id) {
        serviceSale.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Sale> updateSale(@RequestBody Sale sale, @PathVariable Long id) {
        serviceSale.updateSale(sale, id);
        return ResponseEntity.ok(sale);
    }
}
