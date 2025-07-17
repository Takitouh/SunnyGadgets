package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Category;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.ProductMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryCategory;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProvider;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceProduct implements IServiceProduct {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProduct.class);
    private final IRepositoryProduct repositoryProduct;
    private final ProductMapper productMapper;
    private final IRepositoryCategory repositoryCategory;
    private final IRepositoryProvider repositoryProvider;


    public ServiceProduct(IRepositoryProduct repositoryProduct, ProductMapper productMapper, IRepositoryCategory repositoryCategory, IRepositoryProvider repositoryProvider) {
        this.repositoryProduct = repositoryProduct;
        this.productMapper = productMapper;
        this.repositoryCategory = repositoryCategory;
        this.repositoryProvider = repositoryProvider;
    }

    @Override
    public ProductResponseDTO createProduct(ProductCreateDTO product) {
        Product p = productMapper.toEntity(product);
        p = repositoryProduct.save(p);
        ProductResponseDTO responseDTO = productMapper.toDto(p);
        logger.info("Product created: {}", product);
        return responseDTO;
    }

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
        logger.info("Product found: {}", product);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDTO> allProducts() {
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product pr : repositoryProduct.findAll()) {
            productResponseDTOS.add(productMapper.toDto(pr));
        }
        if (productResponseDTOS.isEmpty()) {
            throw new EntityNotFoundException("No product's found"); //Exception Not Found
        }
        logger.info("Get all products from repository");
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO updateProduct(ProductCreateDTO product, Long id) {
        Optional<Product> productOptional = repositoryProduct.findById(id);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found"); //Exception not found
        }
        Set<Provider> providers = new HashSet<>();
        for (Long idP : product.getExistingProvidersIds()){
            providers.add(repositoryProvider.findById(idP).orElseThrow(EntityNotFoundException::new));
        }

        Category category = repositoryCategory.findById(product.getIdCategory()).orElseThrow(EntityNotFoundException::new);
        productOptional.get().setName(product.getName());
        productOptional.get().setDescription(product.getDescription());
        productOptional.get().setPrice(product.getPrice());
        productOptional.get().setStock(product.getStock());
        productOptional.get().setSetProviders(providers);
        productOptional.get().setCategory(category);

        repositoryProduct.save(productOptional.get());
        logger.info("Product updated: {}",  product);
        return productMapper.toDto(productOptional.get());
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = repositoryProduct.findById(id);
        if (productOptional.isEmpty()) {
            throw new EntityNotFoundException("Product with ID " + id + " not found"); //Exception not found
        }
        logger.info("Product deleted: {}", productOptional.get());
        repositoryProduct.deleteById(id);
    }

    @Override
    public List<NameDescriptionPriceProductDTO> findProductsByPrice(long price) {
        return repositoryProduct.findByPriceGreaterThanEqual(price);
    }

    @Override
    public List<NameDescriptionPriceProductDTO> findProductsByCategory(String category) {
       return repositoryProduct.findByCategory(category);
    }


}