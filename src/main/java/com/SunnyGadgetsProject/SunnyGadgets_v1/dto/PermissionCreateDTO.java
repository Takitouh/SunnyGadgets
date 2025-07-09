package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCreateDTO {
    @NotBlank(message = "Permission can't be blank or null") @Nonnull
    private String permissionName;
}
