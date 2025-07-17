package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameTotalSalarySeller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ProviderResponseDTO;

import java.util.List;

public interface IServiceProvider {
    ProviderResponseDTO createProvider(ProviderCreateDTO provider);
    List<ProviderResponseDTO> createProvider(List<ProviderCreateDTO> providers);
    ProviderResponseDTO getProviderById(Long id);
    List<ProviderResponseDTO> allProviders();
    ProviderResponseDTO updateProvider(ProviderCreateDTO provider, Long id);
    void deleteProvider(Long id);

    //Methods for use query's of repository
    List<NameTotalSalarySeller> getProvidersSalary();

}
