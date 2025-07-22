package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwtToken", "status"})
public record AuthResponseDTO(String username, String message, String jwtToken, boolean status) {
}
