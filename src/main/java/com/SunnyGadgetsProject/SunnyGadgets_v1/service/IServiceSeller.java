package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameTotalSalarySeller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerResponseDTO;

import java.util.List;

public interface IServiceSeller {
    SellerResponseDTO createSeller(SellerCreateDTO seller);
    List<SellerResponseDTO> createSeller(List<SellerCreateDTO> sellers);
    SellerResponseDTO getSellerById(Long id);
    List<SellerResponseDTO> allSellers();
    SellerResponseDTO updateSeller(SellerCreateDTO seller, Long id);
    void deleteSeller(Long id);

    //Methods for use query's of repository
    List<NameTotalSalarySeller> getSellersTotalSalary();

}
