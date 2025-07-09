package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleResponseDTO;

import java.util.List;

public interface IServiceDetailSale {


    DetailSaleResponseDTO getDetailSaleById(Long id);
    List<DetailSaleResponseDTO> allDetailSales();


}
