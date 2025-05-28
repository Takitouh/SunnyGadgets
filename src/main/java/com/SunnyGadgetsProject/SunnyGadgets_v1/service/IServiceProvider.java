package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceProvider {
    ResponseEntity<Provider> createProvider(Provider provider);
    ResponseEntity<List<Provider>> createProvider(List<Provider> providers);
    ResponseEntity<Provider> getProviderById(Long id);
    ResponseEntity<List<Provider>> allProviders();
    ResponseEntity<Provider> updateProvider(Provider provider, Long id);
    ResponseEntity<Provider> deleteProvider(Long id);

}
