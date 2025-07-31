package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServicePermission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.ServicePermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/permissions")
public class ControllerPermission {

    private final IServicePermission permissionService;
    private final ServicePermission servicePermission;

    public ControllerPermission(IServicePermission permissionService, ServicePermission servicePermission) {
        this.permissionService = permissionService;
        this.servicePermission = servicePermission;
    }

    @Operation(method = "GET", summary = "Get all permission")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Permissions obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PermissionResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                [
                                {
                                    "idPermission": 1,
                                    "permissionName": "READ"
                                },
                                {
                                    "idPermission": 2,
                                    "permissionName": "DELETE"
                                },
                                {
                                    "idPermission": 3,
                                    "permissionName": "CREATE"
                                },
                                {
                                    "idPermission": 4,
                                    "permissionName": "UPDATE"
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Permission not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Permission not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Permission not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    public ResponseEntity<List<PermissionResponseDTO>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.allPermissions());
    }

    @Operation(method = "GET", summary = "Get one permission by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Permission obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PermissionResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idPermission": 1,
                                "permissionName": "READ"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Permission not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Permission not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Permission not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<PermissionResponseDTO> getPermissionById(@Parameter(description = "id of Permission", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @Operation(method = "POST", summary = "Create one permission")

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Permission created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PermissionResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idPermission": 1,
                                "permissionName": "READ"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })

    @PostMapping("/create")
    public ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody PermissionCreateDTO permission) {
        return new ResponseEntity<>(permissionService.createPermission(permission), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple permissions")

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Permissions created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PermissionResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                [
                                {
                                    "idPermission": 1,
                                    "permissionName": "READ"
                                },
                                {
                                    "idPermission": 2,
                                    "permissionName": "DELETE"
                                },
                                {
                                    "idPermission": 3,
                                    "permissionName": "CREATE"
                                },
                                {
                                    "idPermission": 4,
                                    "permissionName": "UPDATE"
                                }
                                ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })

    @PostMapping("/createBatch")
    public ResponseEntity<List<PermissionResponseDTO>> createPermission(@RequestBody Set<PermissionCreateDTO> permission) {
        return new ResponseEntity<>(permissionService.createPermission(permission), HttpStatus.CREATED);
    }

    @Operation(method = "DELETE", summary = "Delete one permission by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Permission deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Permission not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Permission not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Permission not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PermissionResponseDTO> deletePermission(@Parameter(description = "id of Permission", required = true) @PathVariable Long id) {
        servicePermission.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(method = "PUT", summary = "Update completely a permission by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Permission updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PermissionResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idPermission": 1,
                                "permissionName": "READ"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Permission not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Permission not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Permission not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    public ResponseEntity<PermissionResponseDTO> updatePermission(@Parameter(description = "id of Permission", required = true) @PathVariable Long id, @RequestBody PermissionPutDTO permission) {
        return ResponseEntity.ok(servicePermission.updatePermission(permission, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a permission by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Permission patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PermissionResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idPermission": 1,
                                "permissionName": "READ"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Permission not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Permission not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Permission not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    public ResponseEntity<PermissionResponseDTO> updatePermission(@Parameter(description = "id of Permission", required = true) @PathVariable Long id, @RequestBody PermissionPatchDTO permission) {
        return ResponseEntity.ok(servicePermission.updatePermission(permission, id));
    }
}


