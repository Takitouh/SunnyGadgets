package com.SunnyGadgetsProject.SunnyGadgets_v1.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.SaleMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceSale implements IServiceSale {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSale.class);
    private final IRepositorySale repositorySale;
    private final IRepositoryProduct repositoryProduct;
    private final IRepositorySeller repositorySeller;
    private final SaleMapper saleMapper;


    public ServiceSale(IRepositorySale repositorySale, IRepositoryProduct repositoryProduct, IRepositorySeller repositorySeller, SaleMapper saleMapper) {
        this.repositorySale = repositorySale;
        this.repositoryProduct = repositoryProduct;
        this.repositorySeller = repositorySeller;
        this.saleMapper = saleMapper;
    }

    @Override
    public SaleResponseDTO createSale(SaleCreateDTO sale) {
        Sale sEntity = processSale(sale);

        repositorySale.save(sEntity);

        SaleResponseDTO responseDTO = saleMapper.toDto(sEntity);
        logger.info("Sale and detail sale created: {}", sEntity);
        return responseDTO;
    }

    @Override
    public List<SaleResponseDTO> createSale(List<SaleCreateDTO> sales) {
        List<SaleResponseDTO> responseDTOs = new ArrayList<>();
        List<DetailSale> listdetailSale;
        for (SaleCreateDTO s : sales) {
            listdetailSale = new ArrayList<>();
            Sale sEntity = processSale(s);
            for (DetailSale ds : sEntity.getListdetailSale()) {
                ds.setSale(sEntity);
                listdetailSale.add(ds);
            }
            sEntity.setListdetailSale(listdetailSale);
            repositorySale.save(sEntity);
            responseDTOs.add(saleMapper.toDto(sEntity));
        }
        logger.info("Sales created: {}", sales);
        return responseDTOs;
    }

    @Override
    public SaleResponseDTO getSaleById(Long id) {
        Sale sale = repositorySale.findById(id).orElseThrow(EntityNotFoundException::new);
        return saleMapper.toDto(sale);
    }

    @Override
    public Page<SaleResponseDTO> allSales(Pageable pageable) {
        List<SaleResponseDTO> sales = new ArrayList<>();
        for (Sale s : repositorySale.findAll(pageable)) {
            sales.add(saleMapper.toDto(s));
        }
        if (sales.isEmpty()) {
            throw new EntityNotFoundException("No sales found"); //Exception Not Found
        }
        return new PageImpl<>(sales);
    }

    @Override
    public SaleResponseDTO updateSale(SaleCreateDTO sale, Long id) {
        Sale saleEntity = repositorySale.findById(id).orElseThrow(() -> new EntityNotFoundException("Sale with id " + id + " not found"));
        //Variables for get the objects associated with our sale
        Seller seller = repositorySeller.findById(sale.getIdSeller()).orElseThrow(() -> new EntityNotFoundException("Seller with id " + sale.getIdSeller() + " not found"));

        Sale sEntity = saleMapper.toEntity(sale);
        //Create two List<DetailSale> one for the new details sale and the other one for the old
        List<DetailSale> newDetailSales = calculateTotal(sEntity.getListdetailSale(), saleEntity, seller);
        List<DetailSale> oldDetailSales = saleEntity.getListdetailSale();
        //We assign the new values of new detail sales to old details sale
        for (int i = 0; i < sEntity.getListdetailSale().size(); i++) {
            oldDetailSales.get(i).setSale(newDetailSales.get(i).getSale());
            oldDetailSales.get(i).setSubtotal(newDetailSales.get(i).getSubtotal());
            oldDetailSales.get(i).setUnitPrice(newDetailSales.get(i).getUnitPrice());
            oldDetailSales.get(i).setQuantity(newDetailSales.get(i).getQuantity());
            oldDetailSales.get(i).setProduct(newDetailSales.get(i).getProduct());
        }
        //Assign List<>, Customer and Seller
        saleEntity.setListdetailSale(oldDetailSales);
        saleEntity.setCustomer(sEntity.getCustomer());
        saleEntity.setSeller(sEntity.getSeller());
        repositorySale.save(saleEntity);
        logger.info("Sale updated: {}", sale);
        return saleMapper.toDto(saleEntity);
    }

    @Override
    public void deleteSale(Long id) {
        Optional<Sale> saleOptional = repositorySale.findById(id);
        if (saleOptional.isEmpty()) {
            throw new EntityNotFoundException("Sale not found"); //Exception not found
        }
        logger.info("Sale deleted: {}", saleOptional.get());
        repositorySale.deleteById(id);
    }

    @Override
    public Long totalSold() {
        return repositorySale.totalSales();
    }

    @Override
    public List<NameQuantPurchasesCustomerDTO> getCustomersByPurchases() {
        return repositorySale.findCustomersByPurchases();
    }


    public Sale processSale(SaleCreateDTO sale) {
        //Map the DTOSale to Entity
        Sale saleEntity = saleMapper.toEntity(sale);

        //Variables for handle customer and seller associated with the sale
        Seller seller = repositorySeller.findById(sale.getIdSeller()).orElseThrow(() -> new EntityNotFoundException("Seller with id " + sale.getIdSeller() + " not found"));

        List<DetailSale> detailSale = calculateTotal(saleEntity.getListdetailSale(), saleEntity, seller);

        saleEntity.setListdetailSale(detailSale);

        return saleEntity;
    }

    private List<DetailSale> calculateTotal(List<DetailSale> detailSales, Sale sale, Seller seller) {
        //List<> to iterate across the DetailSale of the sale and calculate the total, subtotal, establish the other
        //attributes of detailsale
        List<DetailSale> auxlistdetailSale = new ArrayList<>();
        long commission = seller.getCommission();
        long total = 0;
        long auxsubtotal;

        for (DetailSale ds : detailSales) {
            //Find product according to ID of detail sale
            Product optionalProduct = repositoryProduct.findById(ds.getProduct().getIdProduct())
                    .orElseThrow(() -> new EntityNotFoundException("Product with id " + ds.getProduct().getIdProduct() + " not found"));
            //Calculate the subtotal with the unit price and the quantity
            auxsubtotal = optionalProduct.getPrice() * ds.getQuantity();
            //Now set the values
            ds.setUnitPrice(optionalProduct.getPrice());
            ds.setSubtotal(auxsubtotal);
            ds.setProduct(optionalProduct);
            ds.setSale(sale);
            //Add the detail sale to the list
            auxlistdetailSale.add(ds);
            //Sum the total
            total += auxsubtotal;
        }
        //All sellers will receive 10% of commission in each sale
        seller.setCommission((long) (total * 0.1) + commission);
        //Assign total
        sale.setTotal(total);

        return auxlistdetailSale;
    }
}