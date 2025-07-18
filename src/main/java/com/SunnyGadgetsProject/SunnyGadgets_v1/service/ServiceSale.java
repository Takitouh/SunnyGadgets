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
    private final IRepositoryCustomer repositoryCustomer;
    private final IRepositoryProduct repositoryProduct;
    private final IRepositorySeller repositorySeller;
    private final SaleMapper saleMapper;
    private final IRepositoryDetailSale repositoryDetailSale;


    public ServiceSale(IRepositorySale repositorySale, IRepositoryCustomer repositoryCustomer, IRepositoryProduct repositoryProduct, IRepositorySeller repositorySeller, SaleMapper saleMapper, IRepositoryDetailSale repositoryDetailSale) {
        this.repositorySale = repositorySale;
        this.repositoryCustomer = repositoryCustomer;
        this.repositoryProduct = repositoryProduct;
        this.repositorySeller = repositorySeller;
        this.saleMapper = saleMapper;
        this.repositoryDetailSale = repositoryDetailSale;
    }

    @Override
    public SaleResponseDTO createSale(SaleCreateDTO sale) {
        Sale sEntity = processSale(sale);

        SaleResponseDTO responseDTO = saleMapper.toDto(sEntity);

        repositorySale.save(sEntity);
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
            responseDTOs.add(saleMapper.toDto(sEntity));
            repositorySale.save(sEntity);
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
        Sale saleEntity = repositorySale.findById(id).orElseThrow(EntityNotFoundException::new);
        //Variables for get the objects relationated with our sale
        Optional<Customer> optionalCustomer = repositoryCustomer.findById(sale.getIdCustomer());
        Optional<Seller> optionalSeller = repositorySeller.findById(sale.getIdSeller());
        //Two Set<> for recover all the sales of Customer and Seller
        //We recover the Sale's that could have the seller or customer
        Set<Sale> salesCustomer = optionalCustomer.map(Customer::getPurchases).orElse(new HashSet<>());
        Set<Sale> salesSeller = optionalSeller.map(Seller::getSales).orElse(new HashSet<>());

        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        } else if (optionalSeller.isEmpty()) {
            throw new RuntimeException("Seller not found");
        }
        //List<> to iterate across the DetailSale of the sale and calculate the total, subtotal, establish the other
        //attributes of detailsale
        List<DetailSale> auxlistdetailSale = new ArrayList<>();
        DetailSale auxdetailSale;
        long total = 0;
        long auxsubtotal;

        for (DetailSaleCreateDTO ds : sale.getListdetailSale()) {
            for (DetailSale dsE : saleEntity.getListdetailSale()) {
                auxdetailSale = repositoryDetailSale.findById(dsE.getId_detailsale()).orElseThrow(EntityNotFoundException::new);
                Optional<Product> optionalProduct = repositoryProduct.findById(ds.getProduct());
                if (optionalProduct.isEmpty()) {
                    throw new RuntimeException("Product not found");
                }
                //Calculate the subtotal with the unit price and the quantity
                auxsubtotal = optionalProduct.get().getPrice() * ds.getQuantity();
                //We set the values for the items of the list detail sale
                auxdetailSale.setUnitPrice(optionalProduct.get().getPrice());
                auxdetailSale.setSubtotal(auxsubtotal);
                auxdetailSale.setProduct(optionalProduct.get());
                auxdetailSale.setQuantity(ds.getQuantity());
                auxdetailSale.setSale(saleEntity);
                //Add all the items to other list
                auxlistdetailSale.add(auxdetailSale);
                //Sum the total
                total += auxdetailSale.getSubtotal();
            }
        }
        /*
        Parece que esta funcionando bien, lo unico seria hacer una refactorizacion luego para mejorar la calidad de codigo
         */
        //Add the new sale and establish the corresponding Set<Sale> to Customer and Seller
        salesCustomer.add(saleEntity);
        salesSeller.add(saleEntity);
        optionalCustomer.get().setPurchases(salesCustomer);
        optionalSeller.get().setSales(salesSeller);
        //All sellers will receive 1% of comission of all sales
        optionalSeller.get().setCommission((long) (total*0.1));
        //And assignate this new list to sale, also assignate the customer and total
        saleEntity.setListdetailSale(auxlistdetailSale);
        saleEntity.setCustomer(optionalCustomer.get());
        //Update the customer with its new purchase
        //repositoryCustomer.save(optionalCustomer.get());
        saleEntity.setSeller(optionalSeller.get());
        saleEntity.setTotal(total);
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
        Sale saleEntity = saleMapper.toEntity(sale);
        //Variables for get the objects relationated with our sale
        Optional<Customer> optionalCustomer = repositoryCustomer.findById(sale.getIdCustomer());
        Optional<Seller> optionalSeller = repositorySeller.findById(sale.getIdSeller());
        //Two Set<> for recover all the sales of Customer and Seller
        //We recover the Sale's that could have the seller or customer
        Set<Sale> salesCustomer = optionalCustomer.map(Customer::getPurchases).orElse(new HashSet<>());
        Set<Sale> salesSeller = optionalSeller.map(Seller::getSales).orElse(new HashSet<>());


        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        } else if (optionalSeller.isEmpty()) {
            throw new RuntimeException("Seller not found");
        }
        //Save because we need that hibernate give him an ID
        repositorySale.save(saleEntity);
        //List<> to iterate across the DetailSale of the sale and calculate the total, subtotal, establish the other
        //attributes of detailsale
        List<DetailSale> auxlistdetailSale = new ArrayList<>();
        long total = 0;
        long auxsubtotal;

        for (DetailSale ds : saleEntity.getListdetailSale()) {
            Optional<Product> optionalProduct = repositoryProduct.findById(ds.getProduct().getIdProduct());
            if (optionalProduct.isEmpty()) {
                throw new RuntimeException("Product not found");
            }
            //Calculate the subtotal with the unit price and the quantity
            auxsubtotal = optionalProduct.get().getPrice() * ds.getQuantity();
            //We set the values for the items of the list detail sale
            ds.setUnitPrice(optionalProduct.get().getPrice());
            ds.setSubtotal(auxsubtotal);
            ds.setProduct(optionalProduct.get());
            ds.setSale(saleEntity);
            //Add all the items to other list
            auxlistdetailSale.add(ds);
            //Sum the total
            total += ds.getSubtotal();
        }
        //Add the new sale and establish the corresponding Set<> to Customer and Seller
        salesCustomer.add(saleEntity);
        salesSeller.add(saleEntity);
        optionalCustomer.get().setPurchases(salesCustomer);
        optionalSeller.get().setSales(salesSeller);
        //All sellers will receive 10% of comission of sales
        optionalSeller.get().setCommission((long) (total*0.1));
        //And assignate this new list to sale, also assignate the customer and total
        saleEntity.setListdetailSale(auxlistdetailSale);
        saleEntity.setCustomer(optionalCustomer.get());
        //Update the customer with its new purchase
        repositoryCustomer.save(optionalCustomer.get());
        saleEntity.setSeller(optionalSeller.get());
        saleEntity.setTotal(total);
        return saleEntity;
    }
}