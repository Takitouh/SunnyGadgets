package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginDTO(@NotBlank String username, @NotBlank String password) {
}
