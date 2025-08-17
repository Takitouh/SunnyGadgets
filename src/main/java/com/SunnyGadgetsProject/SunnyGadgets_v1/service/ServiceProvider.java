package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Product;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.ProviderMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProvider;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceProvider implements IServiceProvider {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProvider.class);
    private final IRepositoryProvider repositoryProvider;
    private final IRepositoryProduct repositoryProduct;

    private final ProviderMapper providerMapper;

    public ServiceProvider(IRepositoryProvider repositoryProvider, IRepositoryProduct repositoryProduct, ProviderMapper providerMapper) {
        this.repositoryProvider = repositoryProvider;
        this.repositoryProduct = repositoryProduct;
        this.providerMapper = providerMapper;
    }

    @Override
    public ProviderResponseDTO createProvider(ProviderCreateDTO providerCreateDTO) {
        Provider provider = providerMapper.toEntity(providerCreateDTO);
        repositoryProvider.save(provider);
        logger.info("Provider created: {}", provider);
        return providerMapper.toDto(provider);
    }

    @Override
    public List<ProviderResponseDTO> createProvider(List<ProviderCreateDTO> providerCreateDTOS) {
        List<ProviderResponseDTO> responses = new ArrayList<>();
        List<Provider> providers = new ArrayList<>();
        for (ProviderCreateDTO providerCreateDTO : providerCreateDTOS) {
            Provider providerEntity = providerMapper.toEntity(providerCreateDTO);
            providers.add(providerEntity);
        }
        repositoryProvider.saveAll(providers);
        for (Provider pr : providers) {
            responses.add(providerMapper.toDto(pr));
        }
        logger.info("List of providers created: {}", providers);
        return responses;
    }

    @Override
    public ProviderResponseDTO getProviderById(Long id) {
        Optional<Provider> provider = repositoryProvider.findById(id);
        if (provider.isEmpty()) {
            throw new EntityNotFoundException("Provider with id " + id + " not found");
        }
        logger.info("Get provider by id: {}", id);
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
        logger.info("All providers found");
        return providerResponseDTOList;
    }

    @Override
    public ProviderResponseDTO updateProvider(ProviderPutDTO providerPutDTO, Long id) {
        Provider provider = repositoryProvider.findById(id).orElseThrow(() -> new EntityNotFoundException("Provider with id: " + id + " not found"));
        Set<Product> products = providerPutDTO.existentProductsIds().stream().map(idProduct -> repositoryProduct.findById(idProduct).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found"))).collect(Collectors.toSet());

        provider.setName(providerPutDTO.name());
        provider.setEmail(providerPutDTO.email());
        provider.setPhoneNumber(providerPutDTO.phoneNumber());
        provider.setSalary(providerPutDTO.salary());
        provider.setProductSet(products);
        provider.setCompany(providerPutDTO.company());

        repositoryProvider.save(provider);
        logger.info("Provider updated with PUT: {}", providerPutDTO);
        return providerMapper.toDto(provider);
    }

    @Override
    public ProviderResponseDTO updateProvider(ProviderPatchDTO providerPatchDTO, Long id) {
        Provider provider = repositoryProvider.findById(id).orElseThrow(() -> new EntityNotFoundException("Provider with id: " + id + " not found"));
        Set<Product> products = providerPatchDTO.existentProductsIds() != null ? providerPatchDTO.existentProductsIds().stream().map(idProduct -> repositoryProduct.findById(idProduct).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " not found"))).collect(Collectors.toSet())
                : provider.getProductSet();

        provider.setName(providerPatchDTO.name() == null || providerPatchDTO.name().isEmpty() ? provider.getName() : providerPatchDTO.name());
        provider.setEmail(providerPatchDTO.email() == null || providerPatchDTO.email().isEmpty() ? provider.getEmail() : providerPatchDTO.email());
        provider.setPhoneNumber(providerPatchDTO.phoneNumber() == null || providerPatchDTO.phoneNumber().isEmpty() ? provider.getPhoneNumber() : providerPatchDTO.phoneNumber());
        provider.setSalary(providerPatchDTO.salary() == null ? provider.getSalary() : providerPatchDTO.salary());
        provider.setProductSet(products);
        provider.setCompany(providerPatchDTO.company() == null || providerPatchDTO.company().isEmpty() ? provider.getCompany() : providerPatchDTO.company());

        repositoryProvider.save(provider);
        logger.info("Provider updated with PATCH: {}", providerPatchDTO);
        return providerMapper.toDto(provider);
    }

    @Override
    public void deleteProvider(Long id) {
        Provider provider = repositoryProvider.findById(id).orElseThrow(() -> new EntityNotFoundException("Provider with id: " + id + " not found"));
        logger.info("Provider deleted: {}", provider);
        repositoryProvider.deleteById(id);
    }

    @Override
    public List<NameTotalSalary> getProvidersSalary() {
        return repositoryProvider.getProvidersSalary();
    }
}
