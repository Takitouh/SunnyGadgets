package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceProduct {
    ResponseEntity<Product> createProduct(Product product);
    ResponseEntity<List<Product>> createProduct(List<Product> products);
    ResponseEntity<Product> getProductById(Long id);
    ResponseEntity<List<Product>> allProducts();
    ResponseEntity<Product> updateProduct(Product product, Long id);
    ResponseEntity<Product> deleteProduct(Long id);

}
