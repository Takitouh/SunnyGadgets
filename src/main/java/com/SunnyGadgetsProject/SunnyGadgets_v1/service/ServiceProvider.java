package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProvider implements IServiceProvider {

    private static final Logger logger = LoggerFactory.getLogger(ServiceProvider.class);
    private final IRepositoryProvider repositoryProvider;

    public ServiceProvider(IRepositoryProvider repositoryProvider) {
        this.repositoryProvider = repositoryProvider;
    }

    @Override
    public Provider createProvider(Provider provider) {
        repositoryProvider.save(provider);
        logger.info("Provider created: {}", provider);
        return provider;
    }

    @Override
    public List<Provider> createProvider(List<Provider> providers) {
        repositoryProvider.saveAll(providers);
        logger.info("Providers created: {}", providers);
        return providers;
    }

    @Override
    public Optional<Provider> getProviderById(Long id) {
        return repositoryProvider.findById(id);
    }

    @Override
    public List<Provider> allProviders() {
        List<Provider> providers = repositoryProvider.findAll();
        if (providers.isEmpty()) {
            return null; //Exception not found
        }
        return providers;
    }

    @Override
    public Provider updateProvider(Provider provider, Long id) {
        Optional<Provider> providerOptional = repositoryProvider.findById(id);
        if (providerOptional.isEmpty()) {
            return null; //Exception not found
        }

        providerOptional.get().setName(provider.getName());
        providerOptional.get().setEmail(provider.getEmail());
        providerOptional.get().setPhone(provider.getPhone());
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
}