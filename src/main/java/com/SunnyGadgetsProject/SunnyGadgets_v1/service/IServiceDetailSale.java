package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceDetailSale {

    ResponseEntity<DetailSale> createDetailSale(DetailSale detailSale);
    ResponseEntity<List<DetailSale>> createDetailSale(List<DetailSale> detailSales);
    ResponseEntity<DetailSale> getDetailSaleById(Long id);
    ResponseEntity<List<DetailSale>> allDetailSales();
    ResponseEntity<DetailSale> updateDetailSale(DetailSale detailSale, Long id);
    ResponseEntity<DetailSale> deleteDetailSale(Long id);

}
