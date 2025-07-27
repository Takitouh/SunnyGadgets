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
import java.util.stream.Collectors;

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
    public ProductResponseDTO createProduct(ProductCreateDTO productCreateDTO) {
        Product p = productMapper.toEntity(productCreateDTO);
        for(Provider provider : p.getSetProviders()){
            Set<Product> products = provider.getProductSet();
            products.add(p);
            provider.setProductSet(products);
        }
        p = repositoryProduct.save(p);
        ProductResponseDTO responseDTO = productMapper.toDto(p);
        logger.info("Product created: {}", productCreateDTO);
        return responseDTO;
    }

    @Override
    public List<ProductResponseDTO> createProduct(List<ProductCreateDTO> productCreateDTOS) {
        List<Product> productList = new ArrayList<>();
        List<ProductResponseDTO> productDTOList = new ArrayList<>();
        Product product;
        for (ProductCreateDTO pro : productCreateDTOS) {
            product = productMapper.toEntity(pro);
            for(Provider provider : product.getSetProviders()){
                Set<Product> products = provider.getProductSet();
                products.add(product);
                provider.setProductSet(products);
            }
            productList.add(product);
        }
        repositoryProduct.saveAll(productList);
        for (Product pro : productList) {
            productDTOList.add(productMapper.toDto(pro));
        }
        logger.info("Products created: {}", productCreateDTOS);
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
    public ProductResponseDTO updateProduct(ProductPutDTO productPutDTO, Long id) {
        Product oldProduct = repositoryProduct.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found"));
        Set<Provider> providers = oldProduct.getSetProviders();
        if(productPutDTO.existingProvidersIds() != null) {
            providers = productPutDTO.existingProvidersIds().stream().map(idProvider -> repositoryProvider.findById(idProvider).orElseThrow(EntityNotFoundException::new)).collect(Collectors.toSet());
        }
        Category category = repositoryCategory.findById(productPutDTO.idCategory()).orElseThrow(EntityNotFoundException::new);

        oldProduct.setName(productPutDTO.name());
        oldProduct.setDescription(productPutDTO.description());
        oldProduct.setPrice(productPutDTO.price());
        oldProduct.setStock(productPutDTO.stock());
        oldProduct.setSetProviders(providers);
        oldProduct.setCategory(category);

        repositoryProduct.save(oldProduct);
        logger.info("Product updated with PUT: {}", productPutDTO);
        return productMapper.toDto(oldProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(ProductPatchDTO productPatchDTO, Long id) {
        Product oldProduct = repositoryProduct.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + "not found"));
        Set<Provider> providers = oldProduct.getSetProviders();
        if(productPatchDTO.existingProvidersIds() != null) {
            providers = productPatchDTO.existingProvidersIds().isEmpty() ? oldProduct.getSetProviders() :
                    productPatchDTO.existingProvidersIds().stream().map(idProvider -> repositoryProvider.findById(idProvider).orElseThrow(EntityNotFoundException::new)).collect(Collectors.toSet());
        }
        Category category = productPatchDTO.idCategory() != null ? repositoryCategory.findById(productPatchDTO.idCategory()).orElseThrow(() -> new EntityNotFoundException("Category with id: " + productPatchDTO.idCategory() + "not found")) : oldProduct.getCategory();
        oldProduct.setName(productPatchDTO.name() == null || productPatchDTO.name().isEmpty()? oldProduct.getName() : productPatchDTO.name());
        oldProduct.setDescription(productPatchDTO.description() == null || productPatchDTO.description().isEmpty()? oldProduct.getDescription() : productPatchDTO.description());
        oldProduct.setPrice(productPatchDTO.price() == null ? oldProduct.getPrice() : productPatchDTO.price());
        oldProduct.setStock(productPatchDTO.stock() == null ? oldProduct.getStock() : productPatchDTO.stock());
        oldProduct.setSetProviders(providers);
        oldProduct.setCategory(category);

        repositoryProduct.save(oldProduct);
        logger.info("Product updated with PATCH: {}", productPatchDTO);
        return productMapper.toDto(oldProduct);    }

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