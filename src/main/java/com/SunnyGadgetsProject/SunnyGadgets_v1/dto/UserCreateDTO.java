package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import com.SunnyGadgetsProject.SunnyGadgets_v1.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    @NotBlank(message = "Username can't be blank or null")
    private String username;
    @NotBlank(message = "Password can't be blank or null")
    private String password;
    @NotNull
    private Boolean enabled;
    @NotNull
    private Boolean accountNotExpired;
    @NotNull
    private Boolean credentialsNotExpired;
    @NotNull
    private Boolean accountNotLocked;

    @NotEmpty(message = "The user must have at least one role") @Valid
    private Set<RoleCreateDTO> roles = new HashSet<>();
}
