package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SellerResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;

import java.util.List;

public interface IServiceSeller {
    SellerResponseDTO createSeller(SellerCreateDTO seller);
    List<SellerResponseDTO> createSeller(List<SellerCreateDTO> sellers);
    SellerResponseDTO getSellerById(Long id);
    List<SellerResponseDTO> allSellers();
    Seller updateSeller(Seller seller, Long id);
    void deleteSeller(Long id);

}
