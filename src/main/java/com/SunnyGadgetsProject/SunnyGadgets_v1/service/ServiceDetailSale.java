package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryDetailSale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DetailSale> createDetailSale(DetailSale detailSale) {
        if (detailSale == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryDetailSale.save(detailSale);
        logger.info("DetailSale created: {}", detailSale);
        return new ResponseEntity<>(detailSale, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<DetailSale>> createDetailSale(List<DetailSale> detailSales) {
        if (detailSales == null || detailSales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositoryDetailSale.saveAll(detailSales);
        logger.info("DetailSales created: {}", detailSales);
        return new ResponseEntity<>(detailSales, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DetailSale> getDetailSaleById(Long id) {
        Optional<DetailSale> detailSale = repositoryDetailSale.findById(id);
        return detailSale.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<DetailSale>> allDetailSales() {
        List<DetailSale> detailSales = repositoryDetailSale.findAll();
        if (detailSales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailSales, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DetailSale> updateDetailSale(DetailSale detailSale, Long id) {
        Optional<DetailSale> detailSaleOptional = repositoryDetailSale.findById(id);
        if (detailSaleOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualiza los campos necesarios:
        detailSaleOptional.get().setQuantity(detailSale.getQuantity());
        detailSaleOptional.get().setUnitPrice(detailSale.getUnitPrice());
        // Agrega otros campos según tu entidad DetailSale

        repositoryDetailSale.save(detailSaleOptional.get());
        logger.info("DetailSale updated: {}", detailSale);
        return new ResponseEntity<>(detailSaleOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DetailSale> deleteDetailSale(Long id) {
        Optional<DetailSale> detailSaleOptional = repositoryDetailSale.findById(id);
        if (detailSaleOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("DetailSale deleted: {}", detailSaleOptional.get());
        repositoryDetailSale.deleteById(id);
        return new ResponseEntity<>(detailSaleOptional.get(), HttpStatus.OK);
    }
}