package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSale implements IServiceSale {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSale.class);
    private final IRepositorySale repositorySale;

    public ServiceSale(IRepositorySale repositorySale) {
        this.repositorySale = repositorySale;
    }

    @Override
    public Sale createSale(Sale sale) {

        repositorySale.save(sale);
        logger.info("Sale created: {}", sale);
        return sale;
    }

    @Override
    public List<Sale> createSale(List<Sale> sales) {
        repositorySale.saveAll(sales);
        logger.info("Sales created: {}", sales);
        return sales;
    }

    @Override
    public Optional<Sale> getSaleById(Long id) {
        return repositorySale.findById(id);
    }

    @Override
    public List<Sale> allSales() {
        List<Sale> sales = repositorySale.findAll();
        if (sales.isEmpty()) {
            return null; //Exception not found
        }
        return sales;
    }

    @Override
    public Sale updateSale(Sale sale, Long id) {
        Optional<Sale> saleOptional = repositorySale.findById(id);
        if (saleOptional.isEmpty()) {
            return null; //Exception not found
        }

        saleOptional.get().setListdetailSale(sale.getListdetailSale());
        saleOptional.get().setTotal(sale.getTotal());
        saleOptional.get().setSeller(sale.getSeller());

        repositorySale.save(saleOptional.get());
        logger.info("Sale updated: {}", sale);
        return saleOptional.get();
    }

    @Override
    public void deleteSale(Long id) {
        Optional<Sale> saleOptional = repositorySale.findById(id);
        if (saleOptional.isEmpty()) {
            return; //Exception not found
        }
        logger.info("Sale deleted: {}", saleOptional.get());
        repositorySale.deleteById(id);
    }
}