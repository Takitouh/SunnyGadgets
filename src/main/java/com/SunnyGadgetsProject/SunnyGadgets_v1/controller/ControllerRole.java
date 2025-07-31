package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceRole;
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
@RequestMapping("/api/v1/roles")
public class ControllerRole {

    private final IServiceRole roleService;

    public ControllerRole(IServiceRole roleService) {
        this.roleService = roleService;
    }

    @Operation(method = "GET", summary = "Get all roles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Roles obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RoleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idRole": 1,
                                    "role": "ADMIN",
                                    "permissions": [
                                        {
                                            "idPermission": 4,
                                            "permissionName": "UPDATE"
                                        },
                                        {
                                            "idPermission": 1,
                                            "permissionName": "READ"
                                        },
                                        {
                                            "idPermission": 3,
                                            "permissionName": "CREATE"
                                        },
                                        {
                                            "idPermission": 2,
                                            "permissionName": "DELETE"
                                        }
                                    ]
                                },
                                {
                                    "idRole": 2,
                                    "role": "USER",
                                    "permissions": [
                                        {
                                            "idPermission": 1,
                                            "permissionName": "READ"
                                        }
                                    ]
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Roles not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Roles not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Roles not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.allRoles());
    }

    @Operation(method = "GET", summary = "Get one role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RoleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idRole": 2,
                                "role": "USER",
                                "permissions": [
                                    {
                                        "idPermission": 1,
                                        "permissionName": "READ"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Role not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Role not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@Parameter(description = "ID of role", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @Operation(method = "POST", summary = "Create one role")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Role created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RoleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idRole": 2,
                                "role": "USER",
                                "permissions": [
                                    {
                                        "idPermission": 1,
                                        "permissionName": "READ"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleCreateDTO role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @Operation(method = "POST", summary = "Create multiple roles")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Roles created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RoleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idRole": 1,
                                    "role": "ADMIN",
                                    "permissions": [
                                        {
                                            "idPermission": 4,
                                            "permissionName": "UPDATE"
                                        },
                                        {
                                            "idPermission": 1,
                                            "permissionName": "READ"
                                        },
                                        {
                                            "idPermission": 3,
                                            "permissionName": "CREATE"
                                        },
                                        {
                                            "idPermission": 2,
                                            "permissionName": "DELETE"
                                        }
                                    ]
                                },
                                {
                                    "idRole": 2,
                                    "role": "USER",
                                    "permissions": [
                                        {
                                            "idPermission": 1,
                                            "permissionName": "READ"
                                        }
                                    ]
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/createBatch")
    public ResponseEntity<List<RoleResponseDTO>> createRole(@RequestBody Set<RoleCreateDTO> role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @Operation(method = "PUT", summary = "Update completely a role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RoleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idRole": 2,
                                "role": "USER",
                                "permissions": [
                                    {
                                        "idPermission": 1,
                                        "permissionName": "READ"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Role not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Role not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@Parameter(description = "ID of role", required = true) @PathVariable Long id, @RequestBody RolePutDTO role) {
        return ResponseEntity.ok(roleService.updateRole(role, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RoleResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idRole": 2,
                                "role": "USER",
                                "permissions": [
                                    {
                                        "idPermission": 1,
                                        "permissionName": "READ"
                                    }
                                ]
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Role not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Role not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@Parameter(description = "ID of role", required = true) @PathVariable Long id, @RequestBody RolePatchDTO role) {
        return ResponseEntity.ok(roleService.updateRole(role, id));
    }

    @Operation(method = "DELETE", summary = "Delete one role by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Role deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Role not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Role not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RoleResponseDTO> deleteRole(@Parameter(description = "ID of role", required = true) @PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
