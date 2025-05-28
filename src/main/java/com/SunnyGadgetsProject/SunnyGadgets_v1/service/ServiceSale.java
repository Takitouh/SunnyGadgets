package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSale implements IServiceSale {
    @Override
    public ResponseEntity<Sale> createSale(Sale sale) {
        return null;
    }

    @Override
    public ResponseEntity<List<Sale>> createSale(List<Sale> sales) {
        return null;
    }

    @Override
    public ResponseEntity<Sale> getSaleById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Sale>> allSales() {
        return null;
    }

    @Override
    public ResponseEntity<Sale> updateSale(Sale sale, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Sale> deleteSale(Long id) {
        return null;
    }
}
