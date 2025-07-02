package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServicePermission;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/permissions")
public class ControllerPermission {

    private final IServicePermission permissionService;

    public ControllerPermission(IServicePermission permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<PermissionResponseDTO>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.allPermissions());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PermissionResponseDTO> getPermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody PermissionCreateDTO permission) {
        return new ResponseEntity<>(permissionService.createPermission(permission), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    public ResponseEntity<List<PermissionResponseDTO>> createPermission(@RequestBody Set<PermissionCreateDTO> permission) {
        return new ResponseEntity<>(permissionService.createPermission(permission), HttpStatus.CREATED);
    }

}


