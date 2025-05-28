package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProduct implements IServiceProduct {
    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> createProduct(List<Product> products) {
        return null;
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Product>> allProducts() {
        return null;
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Product> deleteProduct(Long id) {
        return null;
    }
}
