package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/sale")
public class ControllerSale {
    private final IServiceSale serviceSale;

    public ControllerSale(IServiceSale serviceSale) {
        this.serviceSale = serviceSale;
    }

    @Operation(method = "GET", summary = "Get one role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sale obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSale": 1,
                                "salecreatedAt": "2025-07-27T23:11:41.659+00:00",
                                "total": 1800,
                                "customerName": "Jessica Morales",
                                "sellerName": "Daniela Pérez",
                                "listdetailSale": [
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
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<SaleResponseDTO> getSale(@Parameter(description = "ID of sale", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceSale.getSaleById(id));
    }

    @Operation(method = "GET", summary = "Get all sales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sales obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                    [
                                    {
                                        "idSale": 1,
                                        "salecreatedAt": "2025-07-27T23:11:41.659+00:00",
                                        "total": 1800,
                                        "customerName": "Jessica Morales",
                                        "sellerName": "Daniela Pérez",
                                        "listdetailSale": [
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
                                        ]
                                    }
                                    ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Sales not found")
    })
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Page<SaleResponseDTO>> getAllSales(@PageableDefault(size = 5, sort = "idSale", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(serviceSale.allSales(pageable));
    }

    @Operation(method = "POST", summary = "Create one sale")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sale created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSale": 1,
                                "salecreatedAt": "2025-07-27T23:11:41.659+00:00",
                                "total": 1800,
                                "customerName": "Jessica Morales",
                                "sellerName": "Daniela Pérez",
                                "listdetailSale": [
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
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleCreateDTO sale) {
        return new ResponseEntity<>(serviceSale.createSale(sale), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple sales")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sale created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                    [
                                    {
                                        "idSale": 1,
                                        "salecreatedAt": "2025-07-27T23:11:41.659+00:00",
                                        "total": 1800,
                                        "customerName": "Jessica Morales",
                                        "sellerName": "Daniela Pérez",
                                        "listdetailSale": [
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
                                        ]
                                    }
                                    ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Customer not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Sale not found"
                                              }
                                            """)
                    }))
    })
    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<SaleResponseDTO>> createSales(@RequestBody List<SaleCreateDTO> sales) {
        return new ResponseEntity<>(serviceSale.createSale(sales), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete one sale by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sale deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Sale not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Sale not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Sale not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<SaleResponseDTO> deleteSale(@Parameter(description = "ID of sale", required = true) @PathVariable Long id) {
        serviceSale.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(method = "PUT", summary = "Update completely a sale by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sale updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSale": 1,
                                "salecreatedAt": "2025-07-27T23:11:41.659+00:00",
                                "total": 1800,
                                "customerName": "Jessica Morales",
                                "sellerName": "Daniela Pérez",
                                "listdetailSale": [
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
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SaleResponseDTO> updateSale(@RequestBody SalePutDTO sale, @Parameter(description = "ID of sale", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceSale.updateSale(sale, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a sale by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sale patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSale": 1,
                                "salecreatedAt": "2025-07-27T23:11:41.659+00:00",
                                "total": 1800,
                                "customerName": "Jessica Morales",
                                "sellerName": "Daniela Pérez",
                                "listdetailSale": [
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
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Sale not found")
    })
    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SaleResponseDTO> updateSale(@RequestBody SalePatchDTO sale, @Parameter(description = "ID of sale", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceSale.updateSale(sale, id));
    }

    @Operation(method = "GET", summary = "Get the total sold")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Total sold obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Long.class), examples = {
                    @ExampleObject(value = "6090")
            })),
            @ApiResponse(responseCode = "404", description = "Total not found")
    })
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Long> getTotalSold() {
        return ResponseEntity.ok(serviceSale.totalSold());
    }

    @Operation(method = "GET", summary = "Get the quantity of purchases done by customers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers and N° purchases obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NameQuantPurchasesCustomerDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "name": "Ana María Rodríguez",
                                    "purchases": 1
                                },
                                {
                                    "name": "Luis Fernando Gómez",
                                    "purchases": 1
                                },
                                {
                                    "name": "Jessica Morales",
                                    "purchases": 2
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Customers not found", content = @Content())
    })
    @GetMapping("/find-purchases-customers")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameQuantPurchasesCustomerDTO>> getAllSalesOfCustomers() {
        return ResponseEntity.ok(serviceSale.getCustomersByPurchases());
    }
}
