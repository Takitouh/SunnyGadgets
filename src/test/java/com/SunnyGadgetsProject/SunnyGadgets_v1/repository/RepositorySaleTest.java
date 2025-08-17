package com.SunnyGadgetsProject.SunnyGadgets_v1.repository;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.NameQuantPurchasesCustomerDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import testutils.TestDataBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {IRepositorySale.class, IRepositoryProduct.class,
                IRepositoryCustomer.class, IRepositoryCategory.class, IRepositoryDetailSale.class}))
public class RepositorySaleTest {
    @Autowired
    private IRepositorySale repositorySale;
    @Autowired
    private IRepositoryCustomer repositoryCustomer;
    @Autowired
    private IRepositorySeller repositorySeller;
    @Autowired
    private IRepositoryProduct repositoryProduct;
    @Autowired
    private IRepositoryCategory repositoryCategory;

    private Sale sale;

    @BeforeEach
    void setUp() {

        sale = createSale();
        repositorySale.save(sale);
    }

    @DisplayName(value = "Should return the total sold")
    @Test
    void givenSale_whenFindTotalFromSales_thenReturnTotal() {
        Long result = repositorySale.totalSales();
        assertNotNull(result);
        assertEquals(sale.getTotal(), result);
    }

    @DisplayName(value = "Should return the name and N° of purchases done by each customer")
    @Test
    void givenSale_whenFindCustomerAndPurchases_thenReturnCustomerNameAndPurchases() {
        List<NameQuantPurchasesCustomerDTO> result = repositorySale.findCustomersByPurchases();
        Customer customer = sale.getCustomer();
        assertNotNull(result);
        assertEquals(sale.getListdetailSale().size(), result.get(0).purchases());
        assertEquals(customer.getName(), result.get(0).name());
    }

    private Sale createSale() {
        Customer customer = createCustomer();
        Seller seller = createSeller();
        Product product = createProduct();
        sale = TestDataBuilder.buildSale(customer, seller, product, 200L);
        repositorySale.save(sale);
        return sale;
    }

    private Customer createCustomer() {
        Customer customer = TestDataBuilder.buildCustomer("Mauricio", "mauric@gmail.com", "España", 26);
        repositoryCustomer.save(customer);
        return customer;
    }

    private Seller createSeller() {
        Seller seller = TestDataBuilder.buildSeller("Gustavo", 9500L, 1000L);
        repositorySeller.save(seller);
        return seller;
    }

    private Product createProduct() {
        Category category = createCategory();
        Product product = TestDataBuilder.buildProduct("Screen 18 inches", "Description of the screen 18 inch", 1800L, 14, category);
        repositoryProduct.save(product);
        return product;
    }

    private Category createCategory() {
        Category category = TestDataBuilder.buildCategory("Screens");
        repositoryCategory.save(category);
        return category;
    }

}
