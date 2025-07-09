package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
public class UserSecCreateDTO {
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


    private Set<@NotNull Long> existingRolesIds = new HashSet<>();

}
