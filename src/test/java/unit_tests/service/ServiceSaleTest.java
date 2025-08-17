package unit_tests.service;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.SaleMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositoryProduct;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.repository.IRepositorySeller;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.ServiceSale;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import testutils.TestDataBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ServiceSaleTest {

    @Mock
    private IRepositoryProduct repositoryProduct;

    @Mock
    private SaleMapper saleMapper;

    @Mock
    private IRepositorySale repositorySale;

    @Mock
    private IRepositorySeller repositorySeller;

    @Spy
    @InjectMocks
    private ServiceSale serviceSale;

    private Seller sellerPrincipal;
    private Customer customerPrincipal;
    private Product productPrincipal;
    private Sale salePrincipal;


    @BeforeEach
    void setupSale() {
        sellerPrincipal = TestDataBuilder.buildSeller("Pablo", 60000L, 0L);
        customerPrincipal = TestDataBuilder.buildCustomer("Juan", "Juan@gmail.com", "Perú", 18);
        productPrincipal = TestDataBuilder.buildProduct("Pc gaming", "Description of pc gaming", 1500L, 20, new Category());
        salePrincipal = new Sale(1L, new Timestamp(0), 0, customerPrincipal, sellerPrincipal, new ArrayList<>());

        sellerPrincipal.setIdEmployee(1L);
        customerPrincipal.setIdCustomer(1L);
        productPrincipal.setIdProduct(1L);
    }

    @DisplayName("Should correctly update a sale via put")
    @Test
    void checkPutMethodSale() {
        //I created two new of each one for the new sale
        Seller newSeller = TestDataBuilder.buildSeller("Francis", 50000L, 0L);

        Product newProduct = TestDataBuilder.buildProduct("Laptop", "Description of laptop", 1000L, 15, new Category());
        newProduct.setIdProduct(1L);

        Customer newCustomer = TestDataBuilder.buildCustomer("Sebastian", "sebastian@gmail.com", "Chile", 28);
        //This sale will be the result of map PutDTO
        Sale newSale = TestDataBuilder.buildSale(newCustomer, newSeller, newProduct, 0L);
        newSale.setListdetailSale(TestDataBuilder.buildListDetailSale(newSale, newProduct, 2, 3));
        newSale.setIdSale(1L);

        long total = newProduct.getPrice() * newSale.getListdetailSale().get(0).getQuantity() + newProduct.getPrice() * newSale.getListdetailSale().get(1).getQuantity();

        //I fill the List<> of the oldSale with one detailSale because if it is empty it would throw an exception
        salePrincipal.setListdetailSale(TestDataBuilder.buildListDetailSale(newSale, newProduct, 1, 1));

        when(repositorySale.findById(any(Long.class))).thenReturn(Optional.of(salePrincipal));
        when(repositorySeller.findById(any(Long.class))).thenReturn(Optional.of(newSeller));
        when(repositorySeller.save(any(Seller.class))).thenReturn(sellerPrincipal);
        when(repositoryProduct.findById(any(Long.class))).thenReturn(Optional.of(newProduct));
        when(saleMapper.toEntity(any(SalePutDTO.class))).thenReturn(newSale);
        when(saleMapper.toDto(any(Sale.class))).thenAnswer(invocation -> {
            Sale argument = invocation.getArgument(0);
            return builderSaleResponseDTO(argument);
        });

        SaleResponseDTO result = serviceSale.updateSale(new SalePutDTO(1L, 1L, new ArrayList<>()), 1L);

        verify(repositorySale).findById(anyLong());
        verify(repositorySeller).findById(anyLong());
        verify(saleMapper).toEntity(any(SalePutDTO.class));
        verify(saleMapper).toDto(any(Sale.class));
        verify(repositorySeller).save(any(Seller.class));
        log.info(result.toString());


        assertEquals(total, result.total());
        assertEquals(newCustomer.getName(), result.customerName());
        assertEquals(newSeller.getName(), result.sellerName());
        assertEquals(newSale.getListdetailSale().size(), result.listdetailSale().size());
    }

    @DisplayName("Should correctly update a sale via patch")
    @Test
    void checkPatchMethodSale() {
        //I created two new of each one for the new sale
        Seller newSeller = TestDataBuilder.buildSeller("Francis", 50000L, 0L);

        Product newProduct = TestDataBuilder.buildProduct("Laptop", "Description of laptop", 1000L, 15, new Category());
        newProduct.setIdProduct(1L);

        Customer newCustomer = TestDataBuilder.buildCustomer("Sebastian", "sebastian@gmail.com", "Chile", 28);
        //This sale will be the result of map PutDTO
        Sale newSale = TestDataBuilder.buildSale(newCustomer, newSeller, newProduct, 0L);
        newSale.setListdetailSale(TestDataBuilder.buildListDetailSale(newSale, newProduct, 2, 3));
        newSale.setIdSale(1L);

        long total = newProduct.getPrice() * newSale.getListdetailSale().get(0).getQuantity() + newProduct.getPrice() * newSale.getListdetailSale().get(1).getQuantity();


        //I fill the List<> of the oldSale with one detailSale because if it is empty it would throw an exception
        salePrincipal.setListdetailSale(TestDataBuilder.buildListDetailSale(newSale, newProduct, 1, 1));
        //Stubs
        when(repositorySale.findById(any(Long.class))).thenReturn(Optional.of(salePrincipal));
        when(repositorySeller.findById(any(Long.class))).thenReturn(Optional.of(sellerPrincipal));
        when(repositorySeller.save(any(Seller.class))).thenReturn(sellerPrincipal);
        when(saleMapper.toEntity(any(SalePatchDTO.class))).thenReturn(newSale);
        when(repositoryProduct.findById(any(Long.class))).thenReturn(Optional.of(newProduct));
        //So in this stub is supposed to return the DTO after all the logic
        when(saleMapper.toDto(any(Sale.class))).thenAnswer(invocation -> {
            Sale argument = invocation.getArgument(0);
            return builderSaleResponseDTO(argument);
        });

        SaleResponseDTO result = serviceSale.updateSale(new SalePatchDTO(Optional.of(1L), Optional.of(1L), new ArrayList<>()), 1L);

        verify(repositorySale).findById(1L);
        verify(repositorySeller).findById(1L);
        verify(saleMapper).toEntity(any(SalePatchDTO.class));
        verify(saleMapper).toDto(any(Sale.class));
        verify(repositorySeller).save(any(Seller.class));

        assertEquals(total, result.total());
        assertEquals(newCustomer.getName(), result.customerName());
        assertEquals(newSeller.getName(), result.sellerName());
        assertEquals(newSale.getListdetailSale().size(), result.listdetailSale().size());
    }

    private SaleResponseDTO builderSaleResponseDTO(Sale sale) {
        List<DetailSaleResponseDTO> dtos = new ArrayList<>();
        for (DetailSale ds : sale.getListdetailSale()) {
            ProductResponseDTO productDTO = new ProductResponseDTO(ds.getProduct().getIdProduct(),
                    ds.getProduct().getName(), ds.getProduct().getDescription()
                    , ds.getProduct().getPrice(), ds.getProduct().getStock(), "Gaming");
            DetailSaleResponseDTO dto = new DetailSaleResponseDTO(ds.getQuantity(), ds.getSubtotal(),
                    ds.getUnitPrice(), productDTO);
            dtos.add(dto);
        }
        return new SaleResponseDTO(sale.getIdSale(), sale.getSalecreatedAt(), sale.getTotal(),
                sale.getCustomer().getName(), sale.getSeller().getName(), dtos);
    }

    @DisplayName(value = "Should throw exception if the stock is insufficient")
    @Test
    void productInCalculateTotalAtDetailSaleIsNull() {
        Sale sale = new Sale();
        productPrincipal.setStock(0);

        List<DetailSale> detailSaleList = List.of(new DetailSale(1L, 1, 50, 0, sale, productPrincipal));

        when(repositoryProduct.findById(any(Long.class))).thenReturn(Optional.of(productPrincipal));
        //assertThrows
        RuntimeException exception = assertThrowsExactly(RuntimeException.class, () -> serviceSale.calculateTotal(detailSaleList, sale, sellerPrincipal));
        log.info(exception.getMessage());
    }

    @DisplayName(value = "Throw exception because product wasn't found")
    @Test
    void calculateTotal_ProductDontExistExceptionTest() {
        //Now we create the List<DetailSale> using product and sale
        List<DetailSale> detailSaleList = List.of(new DetailSale(1L, 20, 50, 0, salePrincipal, productPrincipal));
        //Stub the method findById(), and return one Optional.empty() to force the exception since the ID dont exists
        when(repositoryProduct.findById(any(Long.class))).thenReturn(Optional.empty());
        //We store the exception and assert that the exception happens
        EntityNotFoundException exception = assertThrowsExactly(EntityNotFoundException.class, () -> serviceSale.calculateTotal(detailSaleList, salePrincipal, sellerPrincipal));
        //Assert that the message from the exception is correct
        assertEquals(exception.getMessage(), "Product with id " + productPrincipal.getIdProduct() + " not found");
    }

    @DisplayName(value = "Should do correctly the calculation of total")
    @Test
    void calculateTotalTest() {
        //Now we create the List<DetailSale> using product and sale
        List<DetailSale> detailSaleList = TestDataBuilder.buildListDetailSale(salePrincipal, productPrincipal, 1, 1);
        //Stub the method findById(), and return our created product
        when(repositoryProduct.findById(any(Long.class))).thenReturn(Optional.of(productPrincipal));
        //One new List<> is going to store the result of calculateTotal()
        List<DetailSale> result = serviceSale.calculateTotal(detailSaleList, salePrincipal, sellerPrincipal);

        long finalTotal = productPrincipal.getPrice() * 2;
        long finalCommission = (long) (finalTotal * 0.1);

        assertEquals(finalTotal, result.get(0).getSale().getTotal());
        assertEquals(finalCommission, sellerPrincipal.getCommission());
        assertEquals(productPrincipal.getStock(), result.get(0).getProduct().getStock());
        //Verify if our created product was correctly returned
        verify(repositoryProduct, times(2)).findById(anyLong());
    }

    @DisplayName(value = "Should assign customer, detailsales and seller correctly")
    @Test
    void processSale_ShouldAssignSellerAndCustomerAndListDetailSale() {
        List<DetailSale> detailSales = List.of(new DetailSale(1L, 1, 100, 100, salePrincipal, new Product()));

        //We create here the input SaleCreateDTO and it's List<> too
        SaleCreateDTO saleCreateDTO = new SaleCreateDTO(1L, 1L, List.of(new DetailSaleCreateDTO(1, 1L)));
        //Stub the necessary methods
        when(saleMapper.toEntity(any(SaleCreateDTO.class))).thenReturn(salePrincipal);
        when(repositorySeller.findById(any(Long.class))).thenReturn(Optional.of(sellerPrincipal));

        doReturn(detailSales).when(serviceSale).calculateTotal(anyList(), any(Sale.class), any(Seller.class));
        Sale result = serviceSale.processSale(saleCreateDTO);

        verify(saleMapper).toEntity(any(SaleCreateDTO.class));
        verify(repositorySeller).findById(1L);
        verify(serviceSale).calculateTotal(anyList(), any(Sale.class), any(Seller.class));

        //Assert if the return value from the stub of the calculateTotal method is as the same that the list i created detailSales
        assertEquals(detailSales, result.getListdetailSale());
        assertEquals(sellerPrincipal, result.getSeller());
        assertEquals(customerPrincipal, result.getCustomer());
    }
}
