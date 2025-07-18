package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameQuantPurchasesCustomerDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleResponseDTO;

import java.util.List;

public interface IServiceSale {
    SaleResponseDTO createSale(SaleCreateDTO sale);
    List<SaleResponseDTO> createSale(List<SaleCreateDTO> sales);
    SaleResponseDTO getSaleById(Long id);
    Page<SaleResponseDTO> allSales(Pageable pageable);
    SaleResponseDTO updateSale(SaleCreateDTO sale, Long id);
    void deleteSale(Long id);

    //Query's
    Long totalSold();
    List<NameQuantPurchasesCustomerDTO> getCustomersByPurchases();

}
