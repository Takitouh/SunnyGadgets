package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameTotalSalary;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import testutils.TestDataBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class RepositorySellerTest {
    @Autowired
    private IRepositorySeller repositorySeller;

    @Autowired
    private IRepositorySale repositorySale;

    @Autowired
    private IRepositoryCustomer repositoryCustomer;

    @Autowired
    private IRepositoryProduct repositoryProduct;

    @Autowired
    private IRepositoryCategory repositoryCategory;

    private Seller seller;
    private Sale sale;

    @BeforeEach
    void setUp() {
        Category category = TestDataBuilder.buildCategory("Networking");
        repositoryCategory.save(category);
        Product product = TestDataBuilder.buildProduct("Router 5G", "Description Router 5G", 2500L, 7, category);
        repositoryProduct.save(product);
        Customer customer = TestDataBuilder.buildCustomer("Rochelle", "rochel@gmail.com", "Argentina", 32);
        repositoryCustomer.save(customer);
        seller = TestDataBuilder.buildSeller("Francis", 8500L, 1500L);
        repositorySeller.save(seller);
        sale = TestDataBuilder.buildSale(customer, seller, product, 200L);
        repositorySale.save(sale);
    }

    @DisplayName(value = "Should return the total salaries of the sellers")
    @Test
    void whenFindSalaryTotalFromSellers_thenReturnSalaries() {
        List<NameTotalSalary> result = repositorySeller.getSellersSalary();
        long salarySeller = seller.getSalary() + seller.getCommission();
        assertNotNull(result);
        assertEquals(salarySeller, result.get(0).salary());
        assertEquals(seller.getName(), result.get(0).name());
    }

    @DisplayName(value = "Should return all the sales of a seller by his ID")
    @Test
    void givenSeller_whenFindSalesSeller_thenReturnSalesSeller() {
        List<Sale> result = repositorySeller.findSalesSeller(seller.getIdEmployee());

        assertNotNull(result);
        assertEquals(sale, result.get(0));
        assertEquals(seller, result.get(0).getSeller());
    }
}

