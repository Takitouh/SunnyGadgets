package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProduct implements IServiceProduct {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProduct.class);
    private final IRepositoryProduct repositoryProduct;

    public ServiceProduct(IRepositoryProduct repositoryProduct) {
        this.repositoryProduct = repositoryProduct;
    }

    @Override
    public Product createProduct(Product product) {
        repositoryProduct.save(product);
        logger.info("Product created: {}", product);
        return product;
    }

    @Override
    public List<Product> createProduct(List<Product> products) {
        repositoryProduct.saveAll(products);
        logger.info("Products created: {}", products);
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return repositoryProduct.findById(id);
    }

    @Override
    public List<Product> allProducts() {
        List<Product> products = repositoryProduct.findAll();
        if (products.isEmpty()) {
            return null; //Exception not found
        }
        return products;
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