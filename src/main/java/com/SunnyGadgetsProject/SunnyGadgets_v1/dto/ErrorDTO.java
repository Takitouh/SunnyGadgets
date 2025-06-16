package com.SunnyGadgetsProject.SunnyGadgets_v1.dto;

public class ErrorDTO {
    public String code;
    public String message;

    public ErrorDTO() {
        this.code = "";
        this.message = "Something went wrong";
    }

    public ErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
