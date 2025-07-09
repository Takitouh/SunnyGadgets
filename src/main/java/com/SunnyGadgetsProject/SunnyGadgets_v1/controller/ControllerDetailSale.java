package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceDetailSale;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/detailsale")
public class ControllerDetailSale {

    private final IServiceDetailSale serviceDetailSale;

    public ControllerDetailSale(IServiceDetailSale serviceDetailSale) {
        this.serviceDetailSale = serviceDetailSale;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<DetailSaleResponseDTO> getDetailSale(@PathVariable Long id) {
        return ResponseEntity.ok(serviceDetailSale.getDetailSaleById(id));
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<DetailSaleResponseDTO>> getAllDetailSales() {
        return ResponseEntity.ok(serviceDetailSale.allDetailSales());
    }
}


