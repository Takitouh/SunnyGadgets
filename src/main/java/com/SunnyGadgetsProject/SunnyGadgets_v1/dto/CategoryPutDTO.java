package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryPutDTO(@NotBlank(message = "Name can't be blank or null")
                            String name,
                            String description) {
}