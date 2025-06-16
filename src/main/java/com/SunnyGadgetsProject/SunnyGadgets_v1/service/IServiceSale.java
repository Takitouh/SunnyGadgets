package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface IServiceSale {
    Sale createSale(Sale sale);
    List<Sale> createSale(List<Sale> sales);
    Optional<Sale> getSaleById(Long id);
    List<Sale> allSales();
    Sale updateSale(Sale sale, Long id);
    void deleteSale(Long id);

}
