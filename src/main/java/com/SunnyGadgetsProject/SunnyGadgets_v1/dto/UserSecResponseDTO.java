package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

public record UserSecResponseDTO(String user, String password, boolean account_not_expired, boolean account_not_locked,
                                 boolean credentials_not_expired, boolean enabled) {
}
