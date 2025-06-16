package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IServiceProduct {
    Product createProduct(Product product);
    List<Product> createProduct(List<Product> products);
    Optional<Product> getProductById(Long id);
    List<Product> allProducts();
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);

}
