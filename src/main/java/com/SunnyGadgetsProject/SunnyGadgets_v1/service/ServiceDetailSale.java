package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDetailSale implements IServiceDetailSale {
    @Override
    public ResponseEntity<DetailSale> createDetailSale(DetailSale detailSale) {
        return null;
    }

    @Override
    public ResponseEntity<List<DetailSale>> createDetailSale(List<DetailSale> detailSales) {
        return null;
    }

    @Override
    public ResponseEntity<DetailSale> getDetailSaleById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<DetailSale>> allDetailSales() {
        return null;
    }

    @Override
    public ResponseEntity<DetailSale> updateDetailSale(DetailSale detailSale, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<DetailSale> deleteDetailSale(Long id) {
        return null;
    }
}
