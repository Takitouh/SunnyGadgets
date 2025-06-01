package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceSale {
    ResponseEntity<Sale> createSale(Sale sale);
    ResponseEntity<List<Sale>> createSale(List<Sale> sales);
    ResponseEntity<Sale> getSaleById(Long id);
    ResponseEntity<List<Sale>> allSales();
    ResponseEntity<Sale> updateSale(Sale sale, Long id);
    ResponseEntity<Sale> deleteSale(Long id);

}
