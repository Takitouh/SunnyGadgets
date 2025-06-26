package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

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
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RoleCreateDTO {
    @NotNull @NotBlank(message = "The name of the role can't be blank or null")
    private String role;
    @NotEmpty(message = "The role must have at least one permission") @Valid
    private Set<@NotNull Long> permissions = new HashSet<>();
}
