package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Permission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServicePermission;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@RestController
@RequestMapping("/roles")
public class ControllerRole {

    private final IServiceRole roleService;
    private final IServicePermission permissionService;

    public ControllerRole(IServiceRole roleService, IServicePermission permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        // Recuperar la Permission/s por su ID
        for (Permission per : role.getPermissions()) {
            readPermission = permissionService.findById(per.getId()).orElse(null);
            if (readPermission != null) {
                //si encuentro, guardo en la lista
                permissionList.add(readPermission);
            }
        }

        role.setPermissions(permissionList);

        System.out.println(role);
        Role newRole = roleService.save(role);
        return ResponseEntity.ok(newRole);
    }
}
