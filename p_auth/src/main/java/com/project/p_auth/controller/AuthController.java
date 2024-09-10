package com.project.p_auth.controller;

import com.project.p_auth.application.AuthService;
import com.project.p_auth.application.dtos.AuthResponse;
import com.project.p_auth.application.dtos.LoginRequest;
import com.project.p_auth.application.dtos.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("company/register")
    public ResponseEntity<String> companySignUp(@RequestBody SignUpRequest signUpRequest) {

        return ResponseEntity.ok()
                .body(authService.companySignUp(signUpRequest));
    }
    @PostMapping("delivery/register")
    public ResponseEntity<String> deliverySignUp(@RequestBody SignUpRequest signUpRequest) {

        return ResponseEntity.ok()
                .body(authService.deliverySignUp(signUpRequest));
    }
    @PostMapping("hub/register")
    public ResponseEntity<String> hubSignUp(@RequestBody SignUpRequest signUpRequest) {

        return ResponseEntity.ok()
                .body(authService.hubSignUp(signUpRequest));
    }
    @PostMapping("master/register")
    public ResponseEntity<String> masterSignUp(@RequestBody SignUpRequest signUpRequest) {

        return ResponseEntity.ok()
                .body(authService.masterSignUp(signUpRequest));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok()
                .body(authService.signIn(loginRequest));
    }
}
