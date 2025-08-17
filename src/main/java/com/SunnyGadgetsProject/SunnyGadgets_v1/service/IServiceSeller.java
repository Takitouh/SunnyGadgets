package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;

import java.util.List;

public interface IServiceSeller {
    SellerResponseDTO createSeller(SellerCreateDTO seller);
    List<SellerResponseDTO> createSeller(List<SellerCreateDTO> sellers);
    SellerResponseDTO getSellerById(Long id);
    List<SellerResponseDTO> allSellers();
    SellerResponseDTO updateSeller(SellerPutDTO seller, Long id);
    SellerResponseDTO updateSeller(SellerPatchDTO seller, Long id);
    void deleteSeller(Long id);

    //Methods for use query's of repository
    List<NameTotalSalary> getSellersTotalSalary();

    List<SaleResponseDTO> findSalesSeller(Long id);
}
