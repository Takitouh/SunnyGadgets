package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;

import java.util.List;
import java.util.Optional;

public interface IServiceProvider {
    Provider createProvider(Provider provider);
    List<Provider> createProvider(List<Provider> providers);
    Optional<Provider> getProviderById(Long id);
    List<Provider> allProviders();
    Provider updateProvider(Provider provider, Long id);
    void deleteProvider(Long id);

}
