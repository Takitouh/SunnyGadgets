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
    public SaleResponseDTO updateSale(SalePutDTO salePutDTO, Long id) {
        Sale oldSale = repositorySale.findById(id).orElseThrow(() -> new EntityNotFoundException("Sale with id " + id + " not found"));
        //Variables for get the objects associated with our sale
        Seller seller = repositorySeller.findById(salePutDTO.idSeller()).orElseThrow(() -> new EntityNotFoundException("Seller with id " + salePutDTO.idSeller() + " not found"));

        Sale newSale = saleMapper.toEntity(salePutDTO);
        //Create two List<DetailSale> one for the new details sale and the other one for the old
        List<DetailSale> newDetailSales = calculateTotal(newSale.getListdetailSale(), oldSale, seller);
        List<DetailSale> oldDetailSales = oldSale.getListdetailSale();
        Seller oldSeller = oldSale.getSeller();
        oldSeller.setCommission((long) (oldSeller.getCommission() - (oldSale.getTotal() * 0.1)));
        repositorySeller.save(oldSeller);

        if (newDetailSales.size() != oldDetailSales.size())
            throw new RuntimeException("There is a mismatch with the size of new detail sale and the old detail sale");
        Product oldProduct;
        for (int i = 0; i < newDetailSales.size(); i++) {
            newDetailSales.get(i).setSale(oldDetailSales.get(i).getSale());
            //Here we restore the previous stock
            oldProduct = oldDetailSales.get(i).getProduct();
            oldProduct.setStock(oldProduct.getStock() + oldDetailSales.get(i).getQuantity());
        }
        //We assign the new values of new detail sales to old details sale
        for (int i = 0; i < newSale.getListdetailSale().size(); i++) {
            oldDetailSales.get(i).setSale(newDetailSales.get(i).getSale());
            oldDetailSales.get(i).setSubtotal(newDetailSales.get(i).getSubtotal());
            oldDetailSales.get(i).setUnitPrice(newDetailSales.get(i).getUnitPrice());
            oldDetailSales.get(i).setQuantity(newDetailSales.get(i).getQuantity());
            oldDetailSales.get(i).setProduct(newDetailSales.get(i).getProduct());
        }
        //Assign List<>, Customer and Seller
        oldSale.setListdetailSale(oldDetailSales);
        oldSale.setCustomer(newSale.getCustomer());
        oldSale.setSeller(newSale.getSeller());
        repositorySale.save(oldSale);
        logger.info("Sale updated with PUT: {}", salePutDTO);
        return saleMapper.toDto(oldSale);
    }

    @Override
    public SaleResponseDTO updateSale(SalePatchDTO salePatchDTO, Long id) {
        Sale oldSale = repositorySale.findById(id).orElseThrow(() -> new EntityNotFoundException("Sale with id " + id + " not found"));
        //Variables for get the objects associated with our sale
        Seller seller = salePatchDTO.idSeller().isEmpty() ? oldSale.getSeller() : repositorySeller.findById(salePatchDTO.idSeller().get()).orElseThrow(EntityNotFoundException::new);

        Sale newSale = saleMapper.toEntity(salePatchDTO);
        List<DetailSale> oldDetailSales = oldSale.getListdetailSale();

        Seller oldSeller = oldSale.getSeller();
        oldSeller.setCommission((long) (oldSeller.getCommission() - (oldSale.getTotal() * 0.1)));
        repositorySeller.save(oldSeller);

        if (!newSale.getListdetailSale().isEmpty()) {
            //Create two List<DetailSale> one for the new details sale and the other one for the old
            List<DetailSale> newDetailSales = newSale.getListdetailSale();
            if (newDetailSales.size() != oldDetailSales.size())
                throw new RuntimeException("There is a mismatch with the size of new detail sale and the old detail sale");
            Product oldProduct;
            for (int i = 0; i < newDetailSales.size(); i++) {
                newDetailSales.get(i).setSale(oldDetailSales.get(i).getSale());
                //Here we restore the previous stock
                oldProduct = oldDetailSales.get(i).getProduct();
                oldProduct.setStock(oldProduct.getStock() + oldDetailSales.get(i).getQuantity());

                newDetailSales.get(i).setQuantity(newDetailSales.get(i).getQuantity() == null ? oldDetailSales.get(i).getQuantity() : newDetailSales.get(i).getQuantity());
                newDetailSales.get(i).setProduct(newDetailSales.get(i).getProduct() == null ? oldDetailSales.get(i).getProduct() : newDetailSales.get(i).getProduct());
            }

            newDetailSales = calculateTotal(newSale.getListdetailSale(), oldSale, seller);
            //We assign the new values of new detail sales to old details sale
            for (int i = 0; i < newSale.getListdetailSale().size(); i++) {
                oldDetailSales.get(i).setSale(newDetailSales.get(i).getSale());
                oldDetailSales.get(i).setSubtotal(newDetailSales.get(i).getSubtotal());
                oldDetailSales.get(i).setUnitPrice(newDetailSales.get(i).getUnitPrice());
                oldDetailSales.get(i).setQuantity(newDetailSales.get(i).getQuantity());
                oldDetailSales.get(i).setProduct(newDetailSales.get(i).getProduct());
            }
        }
        //Hay un problema con el stock cuando se actualiza y es que se necesita que se revierta el stock, es decir si cambia el stock se debe volver a agregar lo viejo y se resta el stock nuevo

        //Assign List<>, Customer and Seller
        oldSale.setListdetailSale(oldDetailSales);
        oldSale.setCustomer(newSale.getCustomer() == null ? oldSale.getCustomer() : newSale.getCustomer());
        oldSale.setSeller(newSale.getSeller() == null ? oldSale.getSeller() : newSale.getSeller());
        repositorySale.save(oldSale);
        logger.info("Sale updated with PATCH: {}", salePatchDTO);
        return saleMapper.toDto(oldSale);
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
        Seller seller = repositorySeller.findById(sale.idSeller()).orElseThrow(() -> new EntityNotFoundException("Seller with id " + sale.idSeller() + " not found"));

        List<DetailSale> detailSale = calculateTotal(saleEntity.getListdetailSale(), saleEntity, seller);

        saleEntity.setListdetailSale(detailSale);

        return saleEntity;
    }

    private List<DetailSale> calculateTotal(List<DetailSale> detailSales, Sale sale, Seller seller) {
        //List<> to iterate across the DetailSale of the sale and calculate the total, subtotal, establish the other
        //attributes of detailsale
        List<DetailSale> auxlistdetailSale = new ArrayList<>();
        long commission = seller.getCommission() == null? 0 : seller.getCommission();
        long total = 0;
        long auxsubtotal;

        for (DetailSale ds : detailSales) {
            //Find product according to ID of detail sale
            Product optionalProduct = repositoryProduct.findById(ds.getProduct().getIdProduct())
                    .orElseThrow(() -> new EntityNotFoundException("Product with id " + ds.getProduct().getIdProduct() + " not found"));
            if (optionalProduct.getStock() < ds.getQuantity()) {
                throw new RuntimeException("The product " + optionalProduct.getName() + " has not enough stock");
            }
            optionalProduct.setStock(optionalProduct.getStock() - ds.getQuantity());
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
