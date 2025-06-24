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
        Set<Product> products = new HashSet<>();
        Product p;
        for (Product pr : provider.getProductSet()) {
            if (pr == null) {
                throw new EntityNotFoundException("Product is null");
            } else if (pr.getId_product() != null) {
                Optional<Product> product = repositoryProduct.findById(pr.getId_product());
                p = product.get();
            } else {
                p = pr;
            }
            products.add(p);
        }

        provider.setProductSet(products);
        repositoryProvider.saveAndFlush(provider);
        em.detach(provider);

        logger.info("Provider created: {}", provider);
        return provider;
    }

    @Override
    public List<Provider> createProvider(List<Provider> providers) {
        Set<Product> products = new HashSet<>();
        Product p;
        for (Provider provider : providers) {
            Iterator<Product> it = provider.getProductSet().iterator();

            if (it.hasNext()) {
                for (Product pr : provider.getProductSet()) {
                    if (pr == null) {
                        throw new EntityNotFoundException("Product is null");
                    } else if (pr.getId_product() != null) {
                        Optional<Product> product = repositoryProduct.findById(pr.getId_product());
                        p = product.get();
                    } else {
                        p = pr;
                    }
                    products.add(p);
                }
            }
            provider.setProductSet(products);
            repositoryProvider.saveAndFlush(provider);
            em.detach(provider);
            products = new HashSet<>();
        }

        //repositoryProvider.saveAll(providers);
        logger.info("Providers created: {}", providers);
        return providers;
    }

    @Override
    public Optional<Provider> getProviderById(Long id) {
        Optional<Provider> provider = repositoryProvider.findById(id);
        if (provider.isEmpty()) {
            throw new EntityNotFoundException("Provider with id " + id + " not found");
        }
        return provider;
    }

    @Override
    public List<Provider> allProviders() {
        List<Provider> providers = repositoryProvider.findAll();
        if (providers.isEmpty()) {
            throw new EntityNotFoundException("No providers found"); //Exception not found
        }
        return providers;
    }

    @Override
    public Provider updateProvider(Provider provider, Long id) {
        Optional<Provider> providerOptional = repositoryProvider.findById(id);
        if (providerOptional.isEmpty()) {
            throw new EntityNotFoundException("Provider with id " + id + " not found"); //Exception not found
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