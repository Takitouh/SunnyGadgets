package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryDetailSale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDetailSale implements IServiceDetailSale {

    private static final Logger logger = LoggerFactory.getLogger(ServiceDetailSale.class);
    private final IRepositoryDetailSale repositoryDetailSale;

    public ServiceDetailSale(IRepositoryDetailSale repositoryDetailSale) {
        this.repositoryDetailSale = repositoryDetailSale;
    }

    

    @Override
    public Optional<DetailSale> getDetailSaleById(Long id) {
        return repositoryDetailSale.findById(id);
    }

    @Override
    public List<DetailSale> allDetailSales() {
        List<DetailSale> detailSales = repositoryDetailSale.findAll();
        if (detailSales.isEmpty()) {
            return null; //Exception not found
        }
        return detailSales;
    }

    @Override
    public DetailSale updateDetailSale(DetailSale detailSale, Long id) {
        Optional<DetailSale> detailSaleOptional = repositoryDetailSale.findById(id);
        if (detailSaleOptional.isEmpty()) {
            return null; //Exception not found
        }

        // Update the fields
        detailSaleOptional.get().setQuantity(detailSale.getQuantity());
        detailSaleOptional.get().setUnitPrice(detailSale.getUnitPrice());

        repositoryDetailSale.save(detailSaleOptional.get());
        logger.info("DetailSale updated: {}", detailSale);
        return detailSale;
    }

    @Override
    public void deleteDetailSale(Long id) {
        Optional<DetailSale> detailSaleOptional = repositoryDetailSale.findById(id);
        if (detailSaleOptional.isEmpty()) {
            return; //Exception not found
        }
        logger.info("DetailSale deleted: {}", detailSaleOptional.get());
        repositoryDetailSale.deleteById(id);
    }
}