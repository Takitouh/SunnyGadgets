package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/product")
public class ControllerProduct {
    private final IServiceProduct serviceProduct;

    public ControllerProduct(IServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(serviceProduct.getProductById(id));
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(serviceProduct.allProducts());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductCreateDTO product) {
        return new ResponseEntity<>(serviceProduct.createProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<ProductResponseDTO>> createProducts(@RequestBody List<ProductCreateDTO> products) {
        return new ResponseEntity<>(serviceProduct.createProduct(products), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        serviceProduct.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductCreateDTO product, @PathVariable Long id) {
        return ResponseEntity.ok(serviceProduct.updateProduct(product, id));
    }

    @GetMapping("/find-productbyprice/{price}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameDescriptionPriceProductDTO>> findProductsByPrice(@PathVariable long price) {
        return ResponseEntity.ok(serviceProduct.findProductsByPrice(price));
    }

}

