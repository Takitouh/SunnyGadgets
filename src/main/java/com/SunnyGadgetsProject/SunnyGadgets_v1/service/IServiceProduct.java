package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;

import java.util.List;

public interface IServiceProduct {
    ProductResponseDTO createProduct(ProductCreateDTO product);
    List<ProductResponseDTO> createProduct(List<ProductCreateDTO> products);
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> allProducts();
    ProductResponseDTO updateProduct(ProductPutDTO product, Long id);
    ProductResponseDTO updateProduct(ProductPatchDTO product, Long id);
    void deleteProduct(Long id);

    //Query's
    List<NameDescriptionPriceProductDTO> findProductsByPrice(long price);
    List<NameDescriptionPriceProductDTO> findProductsByCategory(String category);
}
