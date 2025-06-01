package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> createProduct(Product product) {
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryProduct.save(product);
        logger.info("Product created: {}", product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Product>> createProduct(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryProduct.saveAll(products);
        logger.info("Products created: {}", products);
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) {
        Optional<Product> product = repositoryProduct.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Product>> allProducts() {
        List<Product> products = repositoryProduct.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product, Long id) {
        Optional<Product> productOptional = repositoryProduct.findById(id);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualiza los campos necesarios:
        productOptional.get().setName(product.getName());
        productOptional.get().setDescription(product.getDescription());
        productOptional.get().setPrice(product.getPrice());
        productOptional.get().setStock(product.getStock());
        productOptional.get().setSetProviders(product.getSetProviders());
        productOptional.get().setCategory(product.getCategory());
        // Agrega otros campos según tu entidad Product

        repositoryProduct.save(productOptional.get());
        logger.info("Product updated: {}",  product);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> deleteProduct(Long id) {
        Optional<Product> productOptional = repositoryProduct.findById(id);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Product deleted: {}", productOptional.get());
        repositoryProduct.deleteById(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
}