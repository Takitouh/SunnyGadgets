package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceDetailSale;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/detailsale")
public class ControllerDetailSale {

    private final IServiceDetailSale serviceDetailSale;

    public ControllerDetailSale(IServiceDetailSale serviceDetailSale) {
        this.serviceDetailSale = serviceDetailSale;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<DetailSale> getDetailSale(@PathVariable Long id) {
        Optional<DetailSale> detailSaleOptional = serviceDetailSale.getDetailSaleById(id);
        if (detailSaleOptional.isEmpty()){
            throw new EntityNotFoundException("Detail Sale with ID " + id + " not found");
        }
        return ResponseEntity.ok(detailSaleOptional.get());
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<DetailSale>> getAllDetailSales() {
        List<DetailSale> detailSales = serviceDetailSale.allDetailSales();
        if (detailSales.isEmpty()){
            throw new EntityNotFoundException("Detail sale list is empty");
        }
        return ResponseEntity.ok(detailSales);
    }




    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<DetailSale> deleteDetailSale(@PathVariable Long id) {
        serviceDetailSale.deleteDetailSale(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<DetailSale> updateDetailSale(@RequestBody DetailSale detailSale, @PathVariable Long id) {
        serviceDetailSale.updateDetailSale(detailSale, id);
        return ResponseEntity.ok(detailSale);
    }
}


