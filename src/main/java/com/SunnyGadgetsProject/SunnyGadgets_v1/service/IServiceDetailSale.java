package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;

import java.util.List;
import java.util.Optional;

public interface IServiceDetailSale {

    DetailSale createDetailSale(DetailSale detailSale);
    List<DetailSale> createDetailSale(List<DetailSale> detailSales);
    Optional<DetailSale> getDetailSaleById(Long id);
    List<DetailSale> allDetailSales();
    DetailSale updateDetailSale(DetailSale detailSale, Long id);
    void deleteDetailSale(Long id);

}
