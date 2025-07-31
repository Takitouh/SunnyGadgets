package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceCategory;
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
@RequestMapping("/api/v1/category")
public class ControllerCategory {
    private final IServiceCategory serviceCategory;

    public ControllerCategory(IServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    @Operation(method = "GET", summary = "Get one category by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idCategory": 5,
                                "name": "Storage",
                                "description": "Hard drives, SSDs, USB sticks, and memory cards",
                                "productSet": [
                                    {
                                        "idProduct": 5,
                                        "name": "2TB External Hard Drive",
                                        "description": "USB 3.1 portable drive",
                                        "price": 130,
                                        "stock": 18,
                                        "category": "Storage"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Category not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Category not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CategoryResponseDTO> getCategory(@Parameter(description = "id of Category", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceCategory.getCategoryById(id));
    }

    @Operation(method = "GET", summary = "Get all categories")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categories obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idCategory": 1,
                                    "name": "Computers",
                                    "description": "Laptops, desktops, and components",
                                    "productSet": [
                                        {
                                            "idProduct": 1,
                                            "name": "Gaming Laptop",
                                            "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                            "price": 1800,
                                            "stock": 7,
                                            "category": "Computers"
                                        }
                                    ]
                                },
                                {
                                    "idCategory": 2,
                                    "name": "Peripherals",
                                    "description": "External devices like mice, keyboards, and monitors",
                                    "productSet": [
                                        {
                                            "idProduct": 2,
                                            "name": "Mechanical Keyboard",
                                            "description": "RGB backlit, blue switches",
                                            "price": 120,
                                            "stock": 25,
                                            "category": "Peripherals"
                                        }
                                    ]
                                }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Categories not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Categories not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Categories not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(serviceCategory.allCategories());
    }

    @Operation(method = "POST", summary = "Create a category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Category created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idCategory": 5,
                                "name": "Storage",
                                "description": "Hard drives, SSDs, USB sticks, and memory cards",
                                "productSet": [
                                    {
                                        "idProduct": 5,
                                        "name": "2TB External Hard Drive",
                                        "description": "USB 3.1 portable drive",
                                        "price": 130,
                                        "stock": 18,
                                        "category": "Storage"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryCreateDTO category) {
        return new ResponseEntity<>(serviceCategory.createCategory(category), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple categories")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categories created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idCategory": 1,
                                    "name": "Computers",
                                    "description": "Laptops, desktops, and components",
                                    "productSet": [
                                        {
                                            "idProduct": 1,
                                            "name": "Gaming Laptop",
                                            "description": "Intel i7, 16GB RAM, RTX 3060, 1TB SSD",
                                            "price": 1800,
                                            "stock": 7,
                                            "category": "Computers"
                                        }
                                    ]
                                },
                                {
                                    "idCategory": 2,
                                    "name": "Peripherals",
                                    "description": "External devices like mice, keyboards, and monitors",
                                    "productSet": [
                                        {
                                            "idProduct": 2,
                                            "name": "Mechanical Keyboard",
                                            "description": "RGB backlit, blue switches",
                                            "price": 120,
                                            "stock": 25,
                                            "category": "Peripherals"
                                        }
                                    ]
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/createBatch")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<List<CategoryResponseDTO>> createCategories(@RequestBody List<CategoryCreateDTO> categories) {
        return new ResponseEntity<>(serviceCategory.createCategory(categories), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete one category by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Category deleted, content = @Content()"),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Category not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Category not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@Parameter(description = "id of Category", required = true) @PathVariable Long id) {
        serviceCategory.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(method = "PUT", summary = "Update completely a category by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Category updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idCategory": 5,
                                "name": "Storage",
                                "description": "Hard drives, SSDs, USB sticks, and memory cards",
                                "productSet": [
                                    {
                                        "idProduct": 5,
                                        "name": "1TB External Hard Drive",
                                        "description": "USB 3.1 portable drive",
                                        "price": 80,
                                        "stock": 25,
                                        "category": "Storage"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Category not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Category not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@RequestBody CategoryPutDTO category, @Parameter(description = "id of Category", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceCategory.updateCategory(category, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a category by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categories obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idCategory": 5,
                                "name": "Storage",
                                "description": "Hard drives, SSDs, USB sticks, and memory cards",
                                "productSet": [
                                    {
                                        "idProduct": 5,
                                        "name": "2TB External Hard Drive",
                                        "description": "USB 3.0 portable drive",
                                        "price": 200,
                                        "stock": 10,
                                        "category": "Storage"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Categories not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Category not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Category not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@RequestBody CategoryPatchDTO category, @Parameter(description = "id of Category", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(serviceCategory.updateCategory(category, id));
    }
}

