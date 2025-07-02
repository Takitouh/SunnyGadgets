package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.ProviderMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceProvider implements IServiceProvider {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProvider.class);
    private final IRepositoryProvider repositoryProvider;
    private final IRepositoryProduct repositoryProduct;
    private final EntityManager em;
    private final ProviderMapper providerMapper;

    public ServiceProvider(IRepositoryProvider repositoryProvider, IRepositoryProduct repositoryProduct, EntityManager em, ProviderMapper providerMapper) {
        this.repositoryProvider = repositoryProvider;
        this.repositoryProduct = repositoryProduct;
        this.em = em;
        this.providerMapper = providerMapper;
    }

    @Override
    public ProviderResponseDTO createProvider(ProviderCreateDTO provider) {
        if (provider.getExistentProductsIds().isEmpty()) {
            throw new EntityNotFoundException("The provider must have at least one product");
        }
        Provider providerEntity = providerMapper.toEntity(provider);
        repositoryProvider.save(providerEntity);
        logger.info("Provider created: {}", provider);
        return providerMapper.toDto(providerEntity);
    }

    @Override
    public List<ProviderResponseDTO> createProvider(List<ProviderCreateDTO> providers) {
        List<ProviderResponseDTO> responses = new ArrayList<>();
        for (ProviderCreateDTO provider : providers) {
            if (provider.getExistentProductsIds().isEmpty()) {
                throw new EntityNotFoundException("The provider must have at least one product");
            }
            Provider providerEntity = providerMapper.toEntity(provider);
            repositoryProvider.save(providerEntity);
            responses.add(providerMapper.toDto(providerEntity));
            logger.info("Provider created: {}", provider);
        }
        return responses;
    }

    @Override
    public ProviderResponseDTO getProviderById(Long id) {
        Optional<Provider> provider = repositoryProvider.findById(id);
        if (provider.isEmpty()) {
            throw new EntityNotFoundException("Provider with id " + id + " not found");
        }
        return providerMapper.toDto(provider.get());
    }

    @Override
    public List<ProviderResponseDTO> allProviders() {
        List<ProviderResponseDTO> providerResponseDTOList = new ArrayList<>();
        List<Provider> providers = repositoryProvider.findAll();
        if (providers.isEmpty()) {
            throw new EntityNotFoundException("No providers found"); //Exception not found
        }
        for (Provider provider : providers) {
            providerResponseDTOList.add(providerMapper.toDto(provider));
        }
        return providerResponseDTOList;
    }

    @Override
    public Provider updateProvider(Provider provider, Long id) {
        Optional<Provider> providerOptional = repositoryProvider.findById(id);
        if (providerOptional.isEmpty()) {
            throw new EntityNotFoundException("Provider with id " + id + " not found"); //Exception not found
        }

        providerOptional.get().setName(provider.getName());
        providerOptional.get().setEmail(provider.getEmail());
        providerOptional.get().setPhoneNumber(provider.getPhoneNumber());
        providerOptional.get().setSalary(provider.getSalary());
        providerOptional.get().setProductSet(provider.getProductSet());


        repositoryProvider.save(providerOptional.get());
        logger.info("Provider updated: {}", provider);
        return provider;
    }

    @Override
    public void deleteProvider(Long id) {
        Optional<Provider> providerOptional = repositoryProvider.findById(id);
        if (providerOptional.isEmpty()) {
            return; //Exception not found
        }
        logger.info("Provider deleted: {}", providerOptional.get());
        repositoryProvider.deleteById(id);
    }

    //Method for recover existent products for a provider

//    public Set<Product> recoverProductsById(Set<Long> ids) {
//        Set<Product> products = new HashSet<>();
//        Product p;
//        for (Long id : ids) {
//            p = repositoryProduct.findById(id).orElseThrow(() -> new EntityNotFoundException("The ID'S doesn't match with any product"));
//            products.add(p);
//        }
//        return products;
//    }
}