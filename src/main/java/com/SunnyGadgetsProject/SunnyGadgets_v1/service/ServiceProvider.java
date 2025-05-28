package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProvider implements IServiceProvider {
    @Override
    public ResponseEntity<Provider> createProvider(Provider provider) {
        return null;
    }

    @Override
    public ResponseEntity<List<Provider>> createProvider(List<Provider> providers) {
        return null;
    }

    @Override
    public ResponseEntity<Provider> getProviderById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Provider>> allProviders() {
        return null;
    }

    @Override
    public ResponseEntity<Provider> updateProvider(Provider provider, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Provider> deleteProvider(Long id) {
        return null;
    }
}
