package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameTotalSalarySeller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderResponseDTO;
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
        return providerMapper.toDto(providerEntity);
    }

    @Override
    public List<ProviderResponseDTO> createProvider(List<ProviderCreateDTO> providers) {
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
    public ProviderResponseDTO updateProvider(ProviderCreateDTO provider, Long id) {
        Optional<Provider> providerOptional = repositoryProvider.findById(id);
        if (providerOptional.isEmpty()) {
            throw new EntityNotFoundException("Provider with id " + id + " not found"); //Exception not found
        }
        Set<Product> products = new HashSet<>();
        for (Long idProduct : provider.getExistentProductsIds()) {
            products.add(repositoryProduct.findById(idProduct).orElseThrow(EntityNotFoundException::new));
        }

        providerOptional.get().setName(provider.getName());
        providerOptional.get().setEmail(provider.getEmail());
        providerOptional.get().setPhoneNumber(provider.getPhoneNumber());
        providerOptional.get().setSalary(provider.getSalary());
        providerOptional.get().setProductSet(products);
        providerOptional.get().setCompany(provider.getCompany());

        repositoryProvider.save(providerOptional.get());
        logger.info("Provider updated: {}", provider);
        return providerMapper.toDto(providerOptional.get());
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

    @Override
    public List<NameTotalSalarySeller> getProvidersSalary() {
        return repositoryProvider.getProvidersSalary();
    }
}