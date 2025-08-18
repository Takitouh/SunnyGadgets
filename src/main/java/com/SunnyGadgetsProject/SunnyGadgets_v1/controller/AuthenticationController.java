package com.SunnyGadgetsProject.SunnyGadgets_v1.controller;

import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.AuthLoginDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.AuthResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.CategoryResponseDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.dto.ErrorDTO;
import com.SunnyGadgetsProject.SunnyGadgets_v1.security.config.UserDetailsServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Operation(method = "POST", summary = "Request a JWT with username and password")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "JWT sent", content =
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CategoryResponseDTO.class), examples = {
                    @ExampleObject(value = """
                            {
                                "username" : "Jhon",
                                "password" : "12345"
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Invalid user or password", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class), examples = {@ExampleObject(value = """
                    {
                        "code": "INTERNAL_ERROR_500",
                        "message": "Invalid username or password"
                    }
                    """)})
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginDTO userRequest) {
        return ResponseEntity.ok().body(userDetailsService.loginUser(userRequest));
    }
}
