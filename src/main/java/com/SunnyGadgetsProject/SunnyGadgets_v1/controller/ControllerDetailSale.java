package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.DetailSaleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ErrorDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceDetailSale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/detailsale")
public class ControllerDetailSale {

    private final IServiceDetailSale serviceDetailSale;

    public ControllerDetailSale(IServiceDetailSale serviceDetailSale) {
        this.serviceDetailSale = serviceDetailSale;
    }

    @Operation(method = "GET", summary = "Get one detail sale by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detail sale obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DetailSaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "quantity": 1,
                                "subtotal": 1800,
                                "unitPrice": 1800,
                                "product": {
                                    "idProduct": 1,
                                    "name": "Gaming Laptop",
                                    "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                    "price": 1800,
                                    "stock": 7,
                                    "category": "Computers"
                                }
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Detail sale not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Detail sale not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Detail sale not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<DetailSaleResponseDTO> getDetailSale(@Parameter(description = "id of Detail sale", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceDetailSale.getDetailSaleById(id));
    }

    @Operation(method = "GET", summary = "Get all detail sales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Detail sales obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DetailSaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                            {
                                "quantity": 3,
                                "subtotal": 2700,
                                "unitPrice": 900,
                                "product": {
                                    "idProduct": 4,
                                    "name": "Smartphone",
                                    "description": "6.7-inch OLED, 128GB, dual SIM",
                                    "price": 900,
                                    "stock": 12,
                                    "category": "Mobile Devices"
                                }
                            },
                            {
                                "quantity": 4,
                                "subtotal": 1000,
                                "unitPrice": 250,
                                "product": {
                                    "idProduct": 6,
                                    "name": "Noise-Canceling Headphones",
                                    "description": "Wireless over-ear with 30-hour battery life",
                                    "price": 250,
                                    "stock": 6,
                                    "category": "Audio"
                                }
                            }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Detail sales not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Detail sales not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Detail sales not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<DetailSaleResponseDTO>> getAllDetailSales() {
        return ResponseEntity.ok(serviceDetailSale.allDetailSales());
    }
}


