package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity(prePostEnabled = true)
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/product")
public class ControllerProduct {
    private final IServiceProduct serviceProduct;

    public ControllerProduct(IServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return serviceProduct.getProductById(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return serviceProduct.allProducts();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return serviceProduct.createProduct(product);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        return serviceProduct.createProduct(products);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        return serviceProduct.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return serviceProduct.updateProduct(product, id);
    }
}

