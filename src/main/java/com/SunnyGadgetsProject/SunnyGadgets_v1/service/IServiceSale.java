package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleResponseDTO;

import java.util.List;

public interface IServiceSale {
    SaleResponseDTO createSale(SaleCreateDTO sale);
    List<SaleResponseDTO> createSale(List<SaleCreateDTO> sales);
    SaleResponseDTO getSaleById(Long id);
    List<SaleResponseDTO> allSales();
    SaleResponseDTO updateSale(SaleCreateDTO sale, Long id);
    void deleteSale(Long id);

}
