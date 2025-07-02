package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.ProductMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProduct implements IServiceProduct {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProduct.class);
    private final IRepositoryProduct repositoryProduct;
    private final ProductMapper productMapper;


    public ServiceProduct(IRepositoryProduct repositoryProduct, ProductMapper productMapper) {
        this.repositoryProduct = repositoryProduct;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDTO createProduct(ProductCreateDTO product) {
        Product p = productMapper.toEntity(product);
        p = repositoryProduct.save(p);
        ProductResponseDTO responseDTO = productMapper.toDto(p);
        logger.info("Product created: {}", product);
        return responseDTO;
    }

    /*   Arreglar esto
         "idProduct": 2,
        "name": "Mechanical Keyboard",
        "description": "RGB backlit, blue switches",
        "price": 120,
        "stock": 25,
        "categoryResponseDTO": null,
        "setProviders": null
     */

    @Override
    public List<ProductResponseDTO> createProduct(List<ProductCreateDTO> products) {
        List<Product> productList = new ArrayList<>();
        List<ProductResponseDTO> productDTOList = new ArrayList<>();
        for (ProductCreateDTO pro : products) {
            productList.add(productMapper.toEntity(pro));
        }
        repositoryProduct.saveAll(productList);
        for (Product pro : productList) {
            productDTOList.add(productMapper.toDto(pro));
        }
        logger.info("Products created: {}", products);
        return productDTOList;
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = repositoryProduct.findById(id).orElseThrow(EntityNotFoundException::new);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDTO> allProducts() {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product pr : repositoryProduct.findAll()) {
            productResponseDTOS.add(productMapper.toDto(pr));
        }
        if (productResponseDTOS.isEmpty()) {
            throw new EntityNotFoundException("No product's found"); //Excepcion Not Found
        }
        return productResponseDTOS;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Optional<Product> productOptional = repositoryProduct.findById(id);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found"); //Exception not found
        }

        productOptional.get().setName(product.getName());
        productOptional.get().setDescription(product.getDescription());
        productOptional.get().setPrice(product.getPrice());
        productOptional.get().setStock(product.getStock());
        productOptional.get().setSetProviders(product.getSetProviders());
        productOptional.get().setCategory(product.getCategory());

        repositoryProduct.save(productOptional.get());
        logger.info("Product updated: {}",  product);
        return productOptional.get();
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = repositoryProduct.findById(id);
        if (productOptional.isEmpty()) {
            return; //Exception not found
        }
        logger.info("Product deleted: {}", productOptional.get());
        repositoryProduct.deleteById(id);
    }
}