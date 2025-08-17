package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceSeller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity()
@PreAuthorize("denyAll()")
@RequestMapping("/api/v1/seller")
public class ControllerSeller {
    private final IServiceSeller serviceSeller;

    public ControllerSeller(IServiceSeller serviceSeller) {
        this.serviceSeller = serviceSeller;
    }

    @Operation(method = "GET", summary = "Get one seller by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seller obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SellerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSeller": 6,
                                "name": "Sofía Ríos",
                                "phoneNumber": "3124567890",
                                "salary": 2000000,
                                "createdAt": "2025-07-27T23:10:23.874+00:00",
                                "updatedAt": "2025-07-27T23:10:23.875+00:00",
                                "commission": 0
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Seller not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Seller not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Seller not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<SellerResponseDTO> getSeller(@Parameter(description = "ID of seller", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.getSellerById(id));
    }

    @Operation(method = "GET", summary = "Get all sellers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sellers obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SellerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idSeller": 1,
                                    "name": "Carlos Ruiz",
                                    "phoneNumber": "1122334455",
                                    "salary": 1700000,
                                    "createdAt": "2025-07-27T23:10:23.845+00:00",
                                    "updatedAt": "2025-07-27T23:11:17.134+00:00",
                                    "commission": 120
                                },
                                {
                                    "idSeller": 2,
                                    "name": "Lucía Herrera",
                                    "phoneNumber": "3157896541",
                                    "salary": 1950000,
                                    "createdAt": "2025-07-27T23:10:23.849+00:00",
                                    "updatedAt": "2025-07-27T23:11:41.637+00:00",
                                    "commission": 33
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Sellers not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Seller not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Seller not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<SellerResponseDTO>> getAllSellers() {
        return ResponseEntity.ok(serviceSeller.allSellers());
    }

    @Operation(method = "POST", summary = "Create one seller")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Seller created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SellerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSeller": 6,
                                "name": "Sofía Ríos",
                                "phoneNumber": "3124567890",
                                "salary": 2000000,
                                "createdAt": "2025-07-27T23:10:23.874+00:00",
                                "updatedAt": "2025-07-27T23:10:23.875+00:00",
                                "commission": 0
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<SellerResponseDTO> createSeller(@RequestBody SellerCreateDTO seller) {
        return new ResponseEntity<>(serviceSeller.createSeller(seller), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple sellers")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sellers created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SellerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idSeller": 1,
                                    "name": "Carlos Ruiz",
                                    "phoneNumber": "1122334455",
                                    "salary": 1700000,
                                    "createdAt": "2025-07-27T23:10:23.845+00:00",
                                    "updatedAt": "2025-07-27T23:11:17.134+00:00",
                                    "commission": 120
                                },
                                {
                                    "idSeller": 2,
                                    "name": "Lucía Herrera",
                                    "phoneNumber": "3157896541",
                                    "salary": 1950000,
                                    "createdAt": "2025-07-27T23:10:23.849+00:00",
                                    "updatedAt": "2025-07-27T23:11:41.637+00:00",
                                    "commission": 33
                                }
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<SellerResponseDTO>> createSellers(@RequestBody List<SellerCreateDTO> sellers) {
        return new ResponseEntity<>(serviceSeller.createSeller(sellers), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete one seller by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Seller deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Seller not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Seller not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Seller not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<SellerResponseDTO> deleteSeller(@Parameter(description = "ID of seller", required = true) @PathVariable Long id) {
        serviceSeller.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(method = "PUT", summary = "Update completely a seller by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seller updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SellerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSeller": 6,
                                "name": "Sofía Ríos",
                                "phoneNumber": "3124567890",
                                "salary": 2000000,
                                "createdAt": "2025-07-27T23:10:23.874+00:00",
                                "updatedAt": "2025-07-27T23:10:23.875+00:00",
                                "commission": 0
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Seller not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Seller not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Seller not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SellerResponseDTO> updateSeller(@RequestBody SellerPutDTO seller, @Parameter(description = "ID of seller", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.updateSeller(seller, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a seller by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Seller patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SellerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idSeller": 6,
                                "name": "Sofía Ríos",
                                "phoneNumber": "3124567890",
                                "salary": 2000000,
                                "createdAt": "2025-07-27T23:10:23.874+00:00",
                                "updatedAt": "2025-07-27T23:10:23.875+00:00",
                                "commission": 0
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Seller not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Seller not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Seller not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<SellerResponseDTO> updateSeller(@RequestBody SellerPatchDTO seller, @Parameter(description = "ID of seller", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.updateSeller(seller, id));
    }

    @Operation(method = "GET", summary = "Get all salaries of each seller")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sellers and salaries obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NameTotalSalary.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "name": "Carlos Ruiz",
                                    "salary": 1700120
                                },
                                {
                                    "name": "Lucía Herrera",
                                    "salary": 1950033
                                },
                                {
                                    "name": "Manuel Torres",
                                    "salary": 1825296
                                },
                                {
                                    "name": "Daniela Pérez",
                                    "salary": 2100280
                                    }
                             ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Sellers not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Seller not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Seller not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/find-salaries")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameTotalSalary>> getAllTotalSalarySellers() {
        return ResponseEntity.ok(serviceSeller.getSellersTotalSalary());
    }

    @Operation(method = "GET", summary = "Get sales done for sellers by ID")
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
                                },
                                {
                                    "idSale": 4,
                                    "salecreatedAt": "2025-07-27T23:10:53.443+00:00",
                                    "total": 1000,
                                    "customerName": "Jessica Morales",
                                    "sellerName": "Daniela Pérez",
                                    "listdetailSale": [
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
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Sales not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Seller not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Seller not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/find-sales/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<SaleResponseDTO>> getSalesSeller(@Parameter(description = "ID of seller", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceSeller.findSalesSeller(id));
    }
}

