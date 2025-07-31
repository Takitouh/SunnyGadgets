package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.*;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceUserSec;
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

@RestController
@RequestMapping("/api/v1/usersec")
public class ControllerUserSec {

    private final IServiceUserSec userService;

    public ControllerUserSec(IServiceUserSec userService) {
        this.userService = userService;
    }

    @Operation(method = "GET", summary = "Get all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Users obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserSecResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            [
                                {
                                    "idUser": 1,
                                    "username": "Jhon",
                                    "password": "$2a$10$vvyDTI41U/U2gIgK/ZII.uTN5I0W2.ksEf0KfljR2AEVu1cmDh9RK",
                                    "roles": [
                                        {
                                            "idRole": 1,
                                            "role": "ADMIN",
                                            "permissions": [
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
                                        }
                                    ],
                                    "accountNotExpired": true,
                                    "accountNotLocked": true,
                                    "credentialsNotExpired": true,
                                    "enabled": true
                                },
                                {
                                    "idUser": 2,
                                    "username": "User",
                                    "password": "$2a$10$RDIJmt3yISViCOl.e/PuzuQDA8ami1kAHukLVYoKV8b9Ab/iCBcTa",
                                    "roles": [
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
                                    ],
                                    "accountNotExpired": true,
                                    "accountNotLocked": true,
                                    "credentialsNotExpired": true,
                                    "enabled": true
                                }
                            ]
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "Users not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "Users not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get")
    public ResponseEntity<List<UserSecResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @Operation(method = "GET", summary = "Get one user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User obtained", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserSecResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "idUser": 1,
                                "username": "Jhon",
                                "password": "$2a$10$vvyDTI41U/U2gIgK/ZII.uTN5I0W2.ksEf0KfljR2AEVu1cmDh9RK",
                                "roles": [
                                    {
                                        "idRole": 1,
                                        "role": "ADMIN",
                                        "permissions": [
                                            {
                                                "idPermission": 2,
                                                "permissionName": "DELETE"
                                            },
                                            {
                                                "idPermission": 4,
                                                "permissionName": "UPDATE"
                                            },
                                            {
                                                "idPermission": 3,
                                                "permissionName": "CREATE"
                                            },
                                            {
                                                "idPermission": 1,
                                                "permissionName": "READ"
                                            }
                                        ]
                                    }
                                ],
                                "accountNotExpired": true,
                                "accountNotLocked": true,
                                "credentialsNotExpired": true,
                                "enabled": true
                            }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "User not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "User not found"
                                              }
                                            """)
                    }))
    })
    @GetMapping("/get/{id}")
    public ResponseEntity<UserSecResponseDTO> getUserById(@Parameter(description = "ID of user", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(method = "POST", summary = "Create one user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserSecResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                {
                                    "idUser": 1,
                                    "username": "Jhon",
                                    "password": "$2a$10$vvyDTI41U/U2gIgK/ZII.uTN5I0W2.ksEf0KfljR2AEVu1cmDh9RK",
                                    "roles": [
                                        {
                                            "idRole": 1,
                                            "role": "ADMIN",
                                            "permissions": [
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
                                        }
                                    ],
                                    "accountNotExpired": true,
                                    "accountNotLocked": true,
                                    "credentialsNotExpired": true,
                                    "enabled": true
                                }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/create")
    public ResponseEntity<UserSecResponseDTO> createUser(@RequestBody UserSecCreateDTO userSec) {
        return new ResponseEntity<>(userService.createUser(userSec), HttpStatus.CREATED);
    }

    @Operation(method = "PUT", summary = "Update completely a user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserSecResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                {
                                    "idUser": 1,
                                    "username": "Jhon",
                                    "password": "$2a$10$vvyDTI41U/U2gIgK/ZII.uTN5I0W2.ksEf0KfljR2AEVu1cmDh9RK",
                                    "roles": [
                                        {
                                            "idRole": 1,
                                            "role": "ADMIN",
                                            "permissions": [
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
                                        }
                                    ],
                                    "accountNotExpired": true,
                                    "accountNotLocked": true,
                                    "credentialsNotExpired": true,
                                    "enabled": true
                                }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "User not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "User not found"
                                              }
                                            """)
                    }))
    })
    @PutMapping("/put/{id}")
    public ResponseEntity<UserSecResponseDTO> updateUser(@Parameter(description = "ID of user", required = true) @PathVariable Long id, @RequestBody UserSecPutDTO userSec) {
        return ResponseEntity.ok(userService.updateUser(userSec, id));
    }

    @Operation(method = "PATCH", summary = "Partial update a user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User patched", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserSecResponseDTO.class), examples = {
                    @ExampleObject(value = """
                                {
                                    "idUser": 1,
                                    "username": "Jhon",
                                    "password": "$2a$10$vvyDTI41U/U2gIgK/ZII.uTN5I0W2.ksEf0KfljR2AEVu1cmDh9RK",
                                    "roles": [
                                        {
                                            "idRole": 1,
                                            "role": "ADMIN",
                                            "permissions": [
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
                                        }
                                    ],
                                    "accountNotExpired": true,
                                    "accountNotLocked": true,
                                    "credentialsNotExpired": true,
                                    "enabled": true
                                }
                            """)
            })),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "User not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "User not found"
                                              }
                                            """)
                    }))
    })
    @PatchMapping("/patch/{id}")
    public ResponseEntity<UserSecResponseDTO> updateUser(@Parameter(description = "ID of user", required = true) @PathVariable Long id, @RequestBody UserSecPatchDTO userSec) {
        return ResponseEntity.ok(userService.updateUser(userSec, id));
    }

    @Operation(method = "DELETE", summary = "Delete one user by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content()),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class), examples = {
                            @ExampleObject(name = "User not found example",
                                    value = """
                                            {
                                              "code": "404",
                                              "message": "User not found"
                                              }
                                            """)
                    }))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserSecResponseDTO> deleteUser(@Parameter(description = "ID of user", required = true) @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
