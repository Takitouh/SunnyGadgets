package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceSale {
    SaleResponseDTO createSale(SaleCreateDTO sale);
    List<SaleResponseDTO> createSale(List<SaleCreateDTO> sales);
    SaleResponseDTO getSaleById(Long id);
    Page<SaleResponseDTO> allSales(Pageable pageable);
    SaleResponseDTO updateSale(SalePutDTO sale, Long id);
    SaleResponseDTO updateSale(SalePatchDTO sale, Long id);
    void deleteSale(Long id);

    //Query's
    Long totalSold();
    List<NameQuantPurchasesCustomerDTO> getCustomersByPurchases();

}
