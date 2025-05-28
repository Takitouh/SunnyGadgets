package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSale;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return serviceSale.getSaleById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Sale>> getAllSales() {
        return serviceSale.allSales();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {
        return serviceSale.createSale(sale);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Sale>> createSales(@RequestBody List<Sale> sales) {
        return serviceSale.createSale(sales);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Sale> deleteSale(@PathVariable Long id) {
        return serviceSale.deleteSale(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Sale> updateSale(@RequestBody Sale sale, @PathVariable Long id) {
        return serviceSale.updateSale(sale, id);
    }
}
