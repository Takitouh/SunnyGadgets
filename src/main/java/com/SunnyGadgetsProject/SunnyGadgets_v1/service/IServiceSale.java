package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.SaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface IServiceSale {
    SaleResponseDTO createSale(SaleCreateDTO sale);
    List<SaleResponseDTO> createSale(List<SaleCreateDTO> sales);
    SaleResponseDTO getSaleById(Long id);
    List<SaleResponseDTO> allSales();
    Sale updateSale(Sale sale, Long id);
    void deleteSale(Long id);

}
