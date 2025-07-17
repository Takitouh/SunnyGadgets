package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameQuantPurchasesCustomerDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/sale")
public class ControllerSale {
    private final IServiceSale serviceSale;


    public ControllerSale(IServiceSale serviceSale) {
        this.serviceSale = serviceSale;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<SaleResponseDTO> getSale(@PathVariable Long id) {
        return ResponseEntity.ok(serviceSale.getSaleById(id));
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        return ResponseEntity.ok(serviceSale.allSales());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleCreateDTO sale) {
        return new ResponseEntity<>(serviceSale.createSale(sale), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<SaleResponseDTO>> createSales(@RequestBody List<SaleCreateDTO> sales) {
        return new ResponseEntity<>(serviceSale.createSale(sales), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Sale> deleteSale(@PathVariable Long id) {
        serviceSale.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SaleResponseDTO> updateSale(@RequestBody SaleCreateDTO sale, @PathVariable Long id) {
        return ResponseEntity.ok(serviceSale.updateSale(sale, id));
    }

    @GetMapping("/total")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Long> getTotalSold() {
        return ResponseEntity.ok(serviceSale.totalSold());
    }

    @GetMapping("/Npurchasesofcustomers")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameQuantPurchasesCustomerDTO>> getAllSalesOfCustomers() {
        return ResponseEntity.ok(serviceSale.getCustomersByPurchases());
    }
}
