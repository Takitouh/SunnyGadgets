package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProduct;
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
@RequestMapping("/api/v1/product")
public class ControllerProduct {
    private final IServiceProduct serviceProduct;

    public ControllerProduct(IServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> productOptional = serviceProduct.getProductById(id);
        if (productOptional.isEmpty()){
            throw new EntityNotFoundException("Product with ID " + id + " not found");
        }
        return ResponseEntity.ok(productOptional.get());
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = serviceProduct.allProducts();
        if (products.isEmpty()){
            throw new EntityNotFoundException("Products list is empty");
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        serviceProduct.createProduct(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        serviceProduct.createProduct(products);

        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        serviceProduct.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        serviceProduct.updateProduct(product, id);
        return ResponseEntity.ok(product);
    }
}

