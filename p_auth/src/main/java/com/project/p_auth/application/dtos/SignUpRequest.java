package com.project.p_auth.application.dtos;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$")
    private String username;

    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9_#$%^!-]{8,15}$")
    private String password;

    private String slack_id;
    private String phone;
    private String email;


}
