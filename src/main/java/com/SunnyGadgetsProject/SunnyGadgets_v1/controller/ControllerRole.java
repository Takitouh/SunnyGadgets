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
//        Set<Permission> permissionList = new HashSet<>();
//        Permission readPermission;
//
//        // Recuperar la Permission/s por su ID
//        for (Permission per : role.getPermissions()) {
//            readPermission = permissionService.getPermissionById(per.getId()).orElse(null);
//            if (readPermission != null) {
//                //si encuentro, guardo en la lista
//                permissionList.add(readPermission);
//            }
//        }
//
//        role.setPermissions(permissionList);
//
//        Role newRole = roleService.createRole(role);
//        return ResponseEntity.ok(newRole);
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    public ResponseEntity<List<RoleResponseDTO>> createRole(@RequestBody Set<RoleCreateDTO> role) {
//        Set<Permission> permissionSet = new HashSet<>();
//        Permission readPermission;
//
//        // Recuperar la Permission/s por su ID
//        for (Role ro : role) {
//            for (Permission per : ro.getPermissions()) {
//                readPermission = permissionService.getPermissionById(per.getId()).orElse(null);
//                if (readPermission != null) {
//                    //si encuentro, guardo en la lista
//                    permissionSet.add(readPermission);
//                }
//            }
//
//
//            ro.setPermissions(permissionSet);
//        }
//
//        List<Role> newRole = roleService.createRole(role);
//        return ResponseEntity.ok(newRole);

        return ResponseEntity.ok(roleService.createRole(role));
    }
}
