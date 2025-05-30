package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Provider> createProvider(Provider provider) {
        if (provider == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryProvider.save(provider);
        logger.info("Provider created: {}", provider);
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Provider>> createProvider(List<Provider> providers) {
        if (providers == null || providers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryProvider.saveAll(providers);
        logger.info("Providers created: {}", providers);
        return new ResponseEntity<>(providers, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Provider> getProviderById(Long id) {
        Optional<Provider> provider = repositoryProvider.findById(id);
        return provider.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Provider>> allProviders() {
        List<Provider> providers = repositoryProvider.findAll();
        if (providers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Provider> updateProvider(Provider provider, Long id) {
        Optional<Provider> providerOptional = repositoryProvider.findById(id);
        if (providerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualiza los campos necesarios:
        providerOptional.get().setName(provider.getName());
        providerOptional.get().setEmail(provider.getEmail());
        providerOptional.get().setPhone(provider.getPhone());
        //We have to see if is necessary this because it should update automatically
        providerOptional.get().setModificationDate(provider.getModificationDate());

        providerOptional.get().setProductSet(provider.getProductSet());

        // Agrega otros campos según tu entidad Provider

        repositoryProvider.save(providerOptional.get());
        logger.info("Provider updated: {}", provider);
        return new ResponseEntity<>(providerOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Provider> deleteProvider(Long id) {
        Optional<Provider> providerOptional = repositoryProvider.findById(id);
        if (providerOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Provider deleted: {}", providerOptional.get());
        repositoryProvider.deleteById(id);
        return new ResponseEntity<>(providerOptional.get(), HttpStatus.OK);
    }
}