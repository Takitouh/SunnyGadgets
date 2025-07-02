package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Provider;

import java.util.List;

public interface IServiceProvider {
    ProviderResponseDTO createProvider(ProviderCreateDTO provider);
    List<ProviderResponseDTO> createProvider(List<ProviderCreateDTO> providers);
    ProviderResponseDTO getProviderById(Long id);
    List<ProviderResponseDTO> allProviders();
    Provider updateProvider(Provider provider, Long id);
    void deleteProvider(Long id);
    //Set<Product> recoverProductsById(Set<Long> ids);

}
