package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Sale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Sale> createSale(Sale sale) {
        if (sale == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositorySale.save(sale);
        logger.info("Sale created: {}", sale);
        return new ResponseEntity<>(sale, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Sale>> createSale(List<Sale> sales) {
        if (sales == null || sales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        repositorySale.saveAll(sales);
        logger.info("Sales created: {}", sales);
        return new ResponseEntity<>(sales, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Sale> getSaleById(Long id) {
        Optional<Sale> sale = repositorySale.findById(id);
        return sale.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<Sale>> allSales() {
        List<Sale> sales = repositorySale.findAll();
        if (sales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Sale> updateSale(Sale sale, Long id) {
        Optional<Sale> saleOptional = repositorySale.findById(id);
        if (saleOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Actualiza los campos necesarios:
        saleOptional.get().setListdetailSale(sale.getListdetailSale());
        saleOptional.get().setTotal(sale.getTotal());
        saleOptional.get().setSeller(sale.getSeller());
        //We have to see if is necessary this because it should update automatically

        saleOptional.get().setSalecreatedAt(sale.getSalecreatedAt());
        // Agrega otros campos según tu entidad Sale

        repositorySale.save(saleOptional.get());
        logger.info("Sale updated: {}", sale);
        return new ResponseEntity<>(saleOptional.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Sale> deleteSale(Long id) {
        Optional<Sale> saleOptional = repositorySale.findById(id);
        if (saleOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Sale deleted: {}", saleOptional.get());
        repositorySale.deleteById(id);
        return new ResponseEntity<>(saleOptional.get(), HttpStatus.OK);
    }
}