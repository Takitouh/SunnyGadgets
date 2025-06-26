package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Customer;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.DetailSale;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Seller;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;
import java.util.List;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SaleCreateDTO {
    @CurrentTimestamp
    private Timestamp salecreatedAt;
    @Positive(message = "The total can't be negative or zero")
    private long total;

    @NotNull
    private Long idCustomer;

    @NotNull
    private SellerCreateDTO seller;
    @NotNull
    @NotEmpty(message = "The sale must have at least one detail sale") @Valid
    private List<DetailSaleCreateDTO> listdetailSale;
}
