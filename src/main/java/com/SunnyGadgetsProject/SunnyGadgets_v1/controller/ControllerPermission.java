package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;


import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionPatchDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionPutDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.PermissionResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServicePermission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.ServicePermission;
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

    @DeleteMapping("/delete")
    public ResponseEntity<Permission> deletePermission(@PathVariable Long id) {
        servicePermission.deletePermission(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<PermissionResponseDTO> updatePermission(@PathVariable Long id, @RequestBody PermissionPutDTO permission) {
        return ResponseEntity.ok(servicePermission.updatePermission(permission, id));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<PermissionResponseDTO> updatePermission(@PathVariable Long id, @RequestBody PermissionPatchDTO permission) {
        return ResponseEntity.ok(servicePermission.updatePermission(permission, id));
    }
}


