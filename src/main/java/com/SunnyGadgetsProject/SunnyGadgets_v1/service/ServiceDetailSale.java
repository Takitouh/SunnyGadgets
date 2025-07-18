package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.DetailSaleMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryDetailSale;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDetailSale implements IServiceDetailSale {

    private static final Logger logger = LoggerFactory.getLogger(ServiceDetailSale.class);
    private final IRepositoryDetailSale repositoryDetailSale;
    private final DetailSaleMapper detailSaleMapper;

    public ServiceDetailSale(IRepositoryDetailSale repositoryDetailSale, DetailSaleMapper detailSaleMapper) {
        this.repositoryDetailSale = repositoryDetailSale;
        this.detailSaleMapper = detailSaleMapper;
    }

    @Override
    public DetailSaleResponseDTO getDetailSaleById(Long id) {
        logger.info("Retrieving detailSale by id: " + id);
        return detailSaleMapper.toDto(repositoryDetailSale.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<DetailSaleResponseDTO> allDetailSales() {
        List<DetailSaleResponseDTO> detailSaleResponseDTOS = new ArrayList<>();
        for (DetailSale ds : repositoryDetailSale.findAll()) {
            detailSaleResponseDTOS.add(detailSaleMapper.toDto(ds));
        }
        if (detailSaleResponseDTOS.isEmpty()) {
            throw new EntityNotFoundException("No detail sales found"); //Exception Not Found
        }
        logger.info("Retrieving detailSale list");
        return detailSaleResponseDTOS;
    }
}