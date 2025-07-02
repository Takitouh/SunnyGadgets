package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.UserSec;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.CategoryMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.mapper.UserMapper;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceRole;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceUserSec;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/usersec")
public class ControllerUserSec {

    private final IServiceUserSec userService;

    private final IServiceRole roleService;

    public ControllerUserSec(IServiceUserSec userService, IServiceRole roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserSecResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.allUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserSecResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<UserSecResponseDTO> createUser(@RequestBody UserSecCreateDTO userSec) {
        return new ResponseEntity<>(userService.createUser(userSec), HttpStatus.CREATED);

    }
}
