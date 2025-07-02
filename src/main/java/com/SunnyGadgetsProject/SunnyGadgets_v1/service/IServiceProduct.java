package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProductResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;

import java.util.List;

public interface IServiceProduct {
    ProductResponseDTO createProduct(ProductCreateDTO product);
    List<ProductResponseDTO> createProduct(List<ProductCreateDTO> products);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> allProducts();
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);

}
