package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCustomer;
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
@RequestMapping("/api/v1/customer")
public class ControllerCustomer {
    private final IServiceCustomer serviceCustomer;


    public ControllerCustomer(IServiceCustomer serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    @Operation(method = "GET", summary = "Get one customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idCustomer": 2,
                                "name": "Ana María Rodríguez",
                                "address": "Medellín, Colombia",
                                "age": 29,
                                "email": "ana.rodriguez@example.com",
                                "phoneNumber": "3015987745",
                                "createdAt": "2025-07-27T23:09:07.548+00:00",
                                "updatedAt": "2025-07-27T23:09:07.548+00:00"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Customers not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Customers not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@Parameter(description = "id of Customer", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.getCustomerById(id));

    }

    @Operation(method = "GET", summary = "Get all customers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                [
                                {
                                    "idCustomer": 3,
                                    "name": "Luis Fernando Gómez",
                                    "address": "Bogotá, Colombia",
                                    "age": 41,
                                    "email": "luis.fernando@example.com",
                                    "phoneNumber": "3104721839",
                                    "createdAt": "2025-07-27T23:09:07.553+00:00",
                                    "updatedAt": "2025-07-27T23:09:07.553+00:00"
                                },
                                {
                                    "idCustomer": 4,
                                    "name": "Jessica Morales",
                                    "address": "Lima, Perú",
                                    "age": 22,
                                    "email": "jess.morales21@example.com",
                                    "phoneNumber": "9876543210",
                                    "createdAt": "2025-07-27T23:09:07.559+00:00",
                                    "updatedAt": "2025-07-27T23:09:07.559+00:00"
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Customers not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Customers not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Customers not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(serviceCustomer.allCustomers());
    }

    @Operation(method = "POST", summary = "Create one customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idCustomer": 2,
                                "name": "Ana María Rodríguez",
                                "address": "Medellín, Colombia",
                                "age": 29,
                                "email": "ana.rodriguez@example.com",
                                "phoneNumber": "3015987745",
                                "createdAt": "2025-07-27T23:09:07.548+00:00",
                                "updatedAt": "2025-07-27T23:09:07.548+00:00"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerCreateDTO customer) {
        return new ResponseEntity<>(serviceCustomer.createCustomer(customer), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple customers")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customers created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                {
                                    "idCustomer": 3,
                                    "name": "Luis Fernando Gómez",
                                    "address": "Bogotá, Colombia",
                                    "age": 41,
                                    "email": "luis.fernando@example.com",
                                    "phoneNumber": "3104721839",
                                    "createdAt": "2025-07-27T23:09:07.553+00:00",
                                    "updatedAt": "2025-07-27T23:09:07.553+00:00"
                                },
                                {
                                    "idCustomer": 4,
                                    "name": "Jessica Morales",
                                    "address": "Lima, Perú",
                                    "age": 22,
                                    "email": "jess.morales21@example.com",
                                    "phoneNumber": "9876543210",
                                    "createdAt": "2025-07-27T23:09:07.559+00:00",
                                    "updatedAt": "2025-07-27T23:09:07.559+00:00"
                                }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<CustomerResponseDTO>> createCustomer(@RequestBody List<CustomerCreateDTO> customers) {
        return new ResponseEntity<>(serviceCustomer.createCustomer(customers), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete one customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Customer deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Customer not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Customer not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CustomerResponseDTO> deleteCustomer(@Parameter(description = "id of Customer", required = true) @PathVariable Long id) {
        serviceCustomer.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(method = "PUT", summary = "Update completely a customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idCustomer": 2,
                                "name": "Ana Sofia Jaimes",
                                "address": "Cauca, Colombia",
                                "age": 42,
                                "email": "ana.jaimes@example.com",
                                "phoneNumber": "3253987745",
                                "createdAt": "2025-07-27T23:09:07.548+00:00",
                                "updatedAt": "2025-07-27T23:09:07.548+00:00"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Customer not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Customer not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerPutDTO customer, @Parameter(description = "id of Customer", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.updateCustomer(customer, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDTO.class),
                    examples = {
                            @ExampleObject(value = """
                                    {
                                        "idCustomer": 2,
                                        "name": "Ana María Zapata",
                                        "address": "Chia, Colombia",
                                        "age": 22,
                                        "email": "ana.zapata@example.com",
                                        "phoneNumber": "3015486745",
                                        "createdAt": "2025-07-27T23:09:07.548+00:00",
                                        "updatedAt": "2025-07-27T23:09:07.548+00:00"
                                    }
                                    """)
                    })),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Customer not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Customer not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody CustomerPatchDTO customer, @Parameter(description = "id of Customer", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.updateCustomer(customer, id));
    }

    @Operation(method = "GET", summary = "Get customers by theirs age's are greater or equal to parameter")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = NameCustomerDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "name": "German Garmendia"
                                },
                                {
                                    "name": "Luis Fernando Gómez"
                                },
                                {
                                    "name": "Carlos Méndez"
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Invalid age")
    })
    @GetMapping("/findby-age/{age}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<NameCustomerDTO>> getAllCustomersByAge(@Parameter(description = "age of Customer", required = true, example = "18") @PathVariable Integer age) {
        return ResponseEntity.ok(serviceCustomer.findCustomersByAgeGreaterThanEqual(age));
    }

    @Operation(method = "GET", summary = "Get the purchases done by the customers by ID of customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer and n° purchases obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SaleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idSale": 2,
                                    "salecreatedAt": "2025-07-27T23:10:53.386+00:00",
                                    "total": 330,
                                    "customerName": "Ana María Rodríguez",
                                    "sellerName": "Lucía Herrera",
                                    "listdetailSale": [
                                        {
                                            "quantity": 3,
                                            "subtotal": 330,
                                            "unitPrice": 110,
                                            "product": {
                                                "idProduct": 3,
                                                "name": "Wi-Fi 6 Router",
                                                "description": "Dual-band gigabit wireless router",
                                                "price": 110,
                                                "stock": 15,
                                                "category": "Networking"
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
                            @ExampleObject(name = "Sales of customer not found example",
                                    value = """
                                            {
                                                "code": "ENTITY_NOT_FOUND_404",
                                                "message": "No purchases found for customer with id 1"
                                            }
                                            """)
                    }))
    })
    @GetMapping("/find-purchases/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<SaleResponseDTO>> getPurchaseCustomers(@Parameter(description = "id of Customer", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceCustomer.findPurchaseCustomers(id));
    }
}
