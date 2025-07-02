package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecCreateDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.UserSecResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceRole;
import com.SunnyGadgetsProject.SunnyGadgets_v1.service.IServiceUserSec;
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
