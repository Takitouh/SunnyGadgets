package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDTO(@NotBlank(message = "Name can't be blank or null")
                                String name,
                                String description) {

}
