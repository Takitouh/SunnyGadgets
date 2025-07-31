package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProduct;
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
@RequestMapping("/api/v1/product")
public class ControllerProduct {
    private final IServiceProduct serviceProduct;

    public ControllerProduct(IServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @Operation(method = "GET", summary = "Get one product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProduct": 1,
                                "name": "Gaming Laptop",
                                "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                "price": 1800,
                                "stock": 7,
                                "category": "Computers"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Product not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Product not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ProductResponseDTO> getProduct(@Parameter(description = "ID of product", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceProduct.getProductById(id));
    }

    @Operation(method = "GET", summary = "Get all products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                [
                                {
                                    "idProduct": 2,
                                    "name": "Mechanical Keyboard",
                                    "description": "RGB backlit, blue switches",
                                    "price": 120,
                                    "stock": 25,
                                    "category": "Peripherals"
                                },
                                {
                                    "idProduct": 3,
                                    "name": "Wi-Fi 6 Router",
                                    "description": "Dual-band gigabit wireless router",
                                    "price": 110,
                                    "stock": 15,
                                    "category": "Networking"
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Products not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Products not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(serviceProduct.allProducts());
    }

    @Operation(method = "POST", summary = "Create one product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProduct": 1,
                                "name": "Gaming Laptop",
                                "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                "price": 1800,
                                "stock": 7,
                                "category": "Computers"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductCreateDTO product) {
        return new ResponseEntity<>(serviceProduct.createProduct(product), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple products")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Products created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                [
                                {
                                    "idProduct": 2,
                                    "name": "Mechanical Keyboard",
                                    "description": "RGB backlit, blue switches",
                                    "price": 120,
                                    "stock": 25,
                                    "category": "Peripherals"
                                },
                                {
                                    "idProduct": 3,
                                    "name": "Wi-Fi 6 Router",
                                    "description": "Dual-band gigabit wireless router",
                                    "price": 110,
                                    "stock": 15,
                                    "category": "Networking"
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<ProductResponseDTO>> createProducts(@RequestBody List<ProductCreateDTO> products) {
        return new ResponseEntity<>(serviceProduct.createProduct(products), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete one product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Product not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Product not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@Parameter(description = "ID of product", required = true) @PathVariable Long id) {
        serviceProduct.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(method = "PUT", summary = "Update completely a product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProduct": 1,
                                "name": "Gaming Laptop",
                                "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                "price": 1800,
                                "stock": 7,
                                "category": "Computers"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Product not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Product not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductPutDTO product, @Parameter(description = "ID of product", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceProduct.updateProduct(product, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a product by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProduct": 1,
                                "name": "Gaming Laptop",
                                "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                "price": 1800,
                                "stock": 7,
                                "category": "Computers"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Product not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Product not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestBody ProductPatchDTO product, @Parameter(description = "ID of product", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceProduct.updateProduct(product, id));
    }

    @Operation(method = "GET", summary = "Get product by theirs price's are greater or equal to price")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NameDescriptionPriceProductDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "name": "Gaming Laptop",
                                    "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                    "price": 1800
                                },
                                {
                                    "name": "Smartphone",
                                    "description": "6.7-inch OLED, 128GB, dual SIM",
                                    "price": 900
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Invalid price", content = @Content())
    })
    @GetMapping("/findby-productprice/{price}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameDescriptionPriceProductDTO>> findProductsByPrice(@Parameter(description = "Price of product", required = true, example = "500") @PathVariable long price) {
        return ResponseEntity.ok(serviceProduct.findProductsByPrice(price));
    }

    @Operation(method = "GET", summary = "Get products by their category name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NameDescriptionPriceProductDTO.class),
                    examples = {
                            @ExampleObject(value = """
                                    [
                                        {
                                            "name": "Wi-Fi 6 Router",
                                            "description": "Dual-band gigabit wireless router",
                                            "price": 110
                                        }
                                    ]
                                    """)
                    })),
            @ApiResponse(responseCode = "400", description = "Invalid category", content = @Content())
    })
    @GetMapping("/findby-productcategory/{category}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameDescriptionPriceProductDTO>> findProductsByCategory(@Parameter(description = "Name of the category of product", required = true, example = "Peripherals") @PathVariable String category) {
        return ResponseEntity.ok(serviceProduct.findProductsByCategory(category));
    }

}

