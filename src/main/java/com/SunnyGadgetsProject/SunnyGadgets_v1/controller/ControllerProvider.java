package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceProvider;
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
@RequestMapping("/api/v1/provider")
public class ControllerProvider {
    private final IServiceProvider serviceProvider;


    public ControllerProvider(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Operation(method = "GET", summary = "Get one provider by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Provider obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProviderResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProvider": 3,
                                "name": "Juan Pere",
                                "phoneNumber": "3154268594",
                                "email": "juan.perz@example.com",
                                "salary": 2100000,
                                "createdAt": "2025-07-27T23:09:21.355+00:00",
                                "updatedAt": "2025-07-27T23:09:21.355+00:00",
                                "productSet": [
                                    {
                                        "idProduct": 6,
                                        "name": "Noise-Canceling Headphones",
                                        "description": "Wireless over-ear with 30-hour battery life",
                                        "price": 250,
                                        "stock": 6,
                                        "category": "Audio"
                                    }
                                ],
                                "company": "Nvidia"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Provider not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Provider not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Provider not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<ProviderResponseDTO> getProvider(@Parameter(description = "ID of provider", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceProvider.getProviderById(id));
    }

    @Operation(method = "GET", summary = "Get all providers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Providers obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProviderResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idProvider": 1,
                                    "name": "Sofia mesa",
                                    "phoneNumber": "3154868594",
                                    "email": "sofiamesa@example.com",
                                    "salary": 2100000,
                                    "createdAt": "2025-07-27T23:09:21.344+00:00",
                                    "updatedAt": "2025-07-27T23:09:21.344+00:00",
                                    "productSet": [
                                        {
                                            "idProduct": 3,
                                            "name": "Wi-Fi 6 Router",
                                            "description": "Dual-band gigabit wireless router",
                                            "price": 110,
                                            "stock": 15,
                                            "category": "Networking"
                                        },
                                        {
                                            "idProduct": 2,
                                            "name": "Mechanical Keyboard",
                                            "description": "RGB backlit, blue switches",
                                            "price": 120,
                                            "stock": 25,
                                            "category": "Peripherals"
                                        },
                                        {
                                            "idProduct": 1,
                                            "name": "Gaming Laptop",
                                            "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                            "price": 1800,
                                            "stock": 7,
                                            "category": "Computers"
                                        }
                                    ],
                                    "company": "AMD"
                                },
                                {
                                    "idProvider": 2,
                                    "name": "Fabricio Gaviria",
                                    "phoneNumber": "3154268474",
                                    "email": "fabricioga@example.com",
                                    "salary": 2100000,
                                    "createdAt": "2025-07-27T23:09:21.350+00:00",
                                    "updatedAt": "2025-07-27T23:09:21.350+00:00",
                                    "productSet": [
                                        {
                                            "idProduct": 4,
                                            "name": "Smartphone",
                                            "description": "6.7-inch OLED, 128GB, dual SIM",
                                            "price": 900,
                                            "stock": 12,
                                            "category": "Mobile Devices"
                                        },
                                        {
                                            "idProduct": 5,
                                            "name": "2TB External Hard Drive",
                                            "description": "USB 3.1 portable drive",
                                            "price": 130,
                                            "stock": 18,
                                            "category": "Storage"
                                        }
                                    ],
                                    "company": "Nvidia"
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Providers not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Providers not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Providers not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders() {
        return ResponseEntity.ok(serviceProvider.allProviders());
    }

    @Operation(method = "POST", summary = "Create one provider")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Provider created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProviderResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProvider": 3,
                                "name": "Juan Pere",
                                "phoneNumber": "3154268594",
                                "email": "juan.perz@example.com",
                                "salary": 2100000,
                                "createdAt": "2025-07-27T23:09:21.355+00:00",
                                "updatedAt": "2025-07-27T23:09:21.355+00:00",
                                "productSet": [
                                    {
                                        "idProduct": 6,
                                        "name": "Noise-Canceling Headphones",
                                        "description": "Wireless over-ear with 30-hour battery life",
                                        "price": 250,
                                        "stock": 6,
                                        "category": "Audio"
                                    }
                                ],
                                "company": "Nvidia"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ProviderResponseDTO> createProvider(@RequestBody ProviderCreateDTO provider) {
        return new ResponseEntity<>(serviceProvider.createProvider(provider), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple providers")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Providers created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProviderResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idProvider": 1,
                                    "name": "Sofia mesa",
                                    "phoneNumber": "3154868594",
                                    "email": "sofiamesa@example.com",
                                    "salary": 2100000,
                                    "createdAt": "2025-07-27T23:09:21.344+00:00",
                                    "updatedAt": "2025-07-27T23:09:21.344+00:00",
                                    "productSet": [
                                        {
                                            "idProduct": 3,
                                            "name": "Wi-Fi 6 Router",
                                            "description": "Dual-band gigabit wireless router",
                                            "price": 110,
                                            "stock": 15,
                                            "category": "Networking"
                                        },
                                        {
                                            "idProduct": 2,
                                            "name": "Mechanical Keyboard",
                                            "description": "RGB backlit, blue switches",
                                            "price": 120,
                                            "stock": 25,
                                            "category": "Peripherals"
                                        },
                                        {
                                            "idProduct": 1,
                                            "name": "Gaming Laptop",
                                            "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                            "price": 1800,
                                            "stock": 7,
                                            "category": "Computers"
                                        }
                                    ],
                                    "company": "AMD"
                                },
                                {
                                    "idProvider": 2,
                                    "name": "Fabricio Gaviria",
                                    "phoneNumber": "3154268474",
                                    "email": "fabricioga@example.com",
                                    "salary": 2100000,
                                    "createdAt": "2025-07-27T23:09:21.350+00:00",
                                    "updatedAt": "2025-07-27T23:09:21.350+00:00",
                                    "productSet": [
                                        {
                                            "idProduct": 4,
                                            "name": "Smartphone",
                                            "description": "6.7-inch OLED, 128GB, dual SIM",
                                            "price": 900,
                                            "stock": 12,
                                            "category": "Mobile Devices"
                                        },
                                        {
                                            "idProduct": 5,
                                            "name": "2TB External Hard Drive",
                                            "description": "USB 3.1 portable drive",
                                            "price": 130,
                                            "stock": 18,
                                            "category": "Storage"
                                        }
                                    ],
                                    "company": "Nvidia"
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<ProviderResponseDTO>> createProviders(@RequestBody List<ProviderCreateDTO> providers) {
        return new ResponseEntity<>(serviceProvider.createProvider(providers), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete one provider by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Provider deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Provider not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Provider not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Provider not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<ProviderResponseDTO> deleteProvider(@Parameter(description = "ID of provider", required = true) @PathVariable Long id) {
        serviceProvider.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(method = "PUT", summary = "Update completely a provider by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Provider updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProviderResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProvider": 3,
                                "name": "Juan Pere",
                                "phoneNumber": "3154268594",
                                "email": "juan.perz@example.com",
                                "salary": 2100000,
                                "createdAt": "2025-07-27T23:09:21.355+00:00",
                                "updatedAt": "2025-07-27T23:09:21.355+00:00",
                                "productSet": [
                                    {
                                        "idProduct": 6,
                                        "name": "Noise-Canceling Headphones",
                                        "description": "Wireless over-ear with 30-hour battery life",
                                        "price": 250,
                                        "stock": 6,
                                        "category": "Audio"
                                    }
                                ],
                                "company": "Nvidia"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Provider not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Provider not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Provider not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ProviderResponseDTO> updateProvider(@RequestBody ProviderPutDTO provider, @Parameter(description = "ID of provider", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceProvider.updateProvider(provider, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a provider by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Provider patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProviderResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idProvider": 3,
                                "name": "Juan Pere",
                                "phoneNumber": "3154268594",
                                "email": "juan.perz@example.com",
                                "salary": 2100000,
                                "createdAt": "2025-07-27T23:09:21.355+00:00",
                                "updatedAt": "2025-07-27T23:09:21.355+00:00",
                                "productSet": [
                                    {
                                        "idProduct": 6,
                                        "name": "Noise-Canceling Headphones",
                                        "description": "Wireless over-ear with 30-hour battery life",
                                        "price": 250,
                                        "stock": 6,
                                        "category": "Audio"
                                    }
                                ],
                                "company": "Nvidia"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Provider not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Provider not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Provider not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ProviderResponseDTO> updateProvider(@RequestBody ProviderPatchDTO provider, @Parameter(description = "ID of provider", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceProvider.updateProvider(provider, id));
    }

    @Operation(method = "GET", summary = "Get all salaries of the providers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Providers and salaries obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NameTotalSalarySeller.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "name": "Sofia mesa",
                                    "salary": 2100000
                                },
                                {
                                    "name": "Fabricio Gaviria",
                                    "salary": 2100000
                                },
                                {
                                    "name": "Juan Pere",
                                    "salary": 2100000
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Providers not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Providers not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Providers not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/find-salaries")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameTotalSalarySeller>> getAllSalaryProviders() {
        return ResponseEntity.ok(serviceProvider.getProvidersSalary());
    }
}
