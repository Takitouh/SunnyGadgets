package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.RoleResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceRole;
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

    @GetMapping("/get")
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.allRoles());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleCreateDTO role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    public ResponseEntity<List<RoleResponseDTO>> createRole(@RequestBody Set<RoleCreateDTO> role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable Long id, @RequestBody RoleCreateDTO role) {
        return ResponseEntity.ok(roleService.updateRole(role, id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RoleResponseDTO> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
