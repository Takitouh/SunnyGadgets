package testutils;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestDataBuilder {
    public static Customer buildCustomer(String name, String email, String address, int age) {
        return new Customer(null, age, address, name, email, new HashSet<>(), "3154125263",
                null, null);
    }

    public static Seller buildSeller(String name, Long salary, Long commission) {
        Seller seller = new Seller(new HashSet<>(), commission);
        seller.setName(name);
        seller.setSalary(salary);
        seller.setPhoneNumber("3154125263");
        return seller;
    }

    public static Product buildProduct(String name, String description, Long price, int stock, Category category) {
        return new Product(null, name, description, price, stock, category, new HashSet<>());
    }

    public static Category buildCategory(String name) {
        return new Category(null, name, "Description of " + name, new HashSet<>());
    }

    public static Sale buildSale(Customer customer, Seller seller, Product product, Long total) {
        Sale sale = new Sale(null, null, total, customer, seller, new ArrayList<>());
        DetailSale detailSale = new DetailSale(null, 1, 200, 200, sale, product);
        List<DetailSale> detailSaleList = new ArrayList<>();
        detailSaleList.add(detailSale);
        sale.setListdetailSale(detailSaleList);
        if (customer != null) {
            customer.getPurchases().add(sale); // Set bidirectional relationship
        }
        if (seller != null) {
            seller.getSales().add(sale); // Set bidirectional relationship, if applicable
        }
        return sale;
    }

    public static List<DetailSale> buildListDetailSale(Sale sale, Product product, int quantityOne, int quantityTwo) {
        List<DetailSale> detailSaleList = new ArrayList<>();
        detailSaleList.add(new DetailSale(null, quantityOne, product.getPrice(), 0L, sale, product));
        detailSaleList.add(new DetailSale(null, quantityTwo, product.getPrice(), 0L, sale, product));
        return detailSaleList;
    }

}
