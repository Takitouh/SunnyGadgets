package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;

import java.util.List;

public interface IServiceProvider {
    ProviderResponseDTO createProvider(ProviderCreateDTO provider);
    List<ProviderResponseDTO> createProvider(List<ProviderCreateDTO> providers);
    ProviderResponseDTO getProviderById(Long id);
    List<ProviderResponseDTO> allProviders();
    ProviderResponseDTO updateProvider(ProviderPutDTO provider, Long id);
    ProviderResponseDTO updateProvider(ProviderPatchDTO provider, Long id);
    void deleteProvider(Long id);

    //Methods for use query's of repository
    List<NameTotalSalarySeller> getProvidersSalary();

}
