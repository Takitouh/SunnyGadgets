package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceDetailSale;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
        return serviceDetailSale.getDetailSaleById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<DetailSale>> getAllDetailSales() {
        return serviceDetailSale.allDetailSales();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<DetailSale> createDetailSale(@RequestBody DetailSale detailSale) {
        return serviceDetailSale.createDetailSale(detailSale);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<DetailSale>> createDetailSales(@RequestBody List<DetailSale> detailSales) {
        return serviceDetailSale.createDetailSale(detailSales);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<DetailSale> deleteDetailSale(@PathVariable Long id) {
        return serviceDetailSale.deleteDetailSale(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<DetailSale> updateDetailSale(@RequestBody DetailSale detailSale, @PathVariable Long id) {
        return serviceDetailSale.updateDetailSale(detailSale, id);
    }
}


