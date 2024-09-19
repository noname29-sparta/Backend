package com.project.p_auth.controller;

import com.project.p_auth.application.UserService;
import com.project.p_auth.application.dtos.updateRoleRequest;
import com.project.p_auth.application.dtos.userDetailResponse;
import com.project.p_auth.application.dtos.userResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/")

public class UserController {
    private final UserService userService;
    @GetMapping("") // 
    public ResponseEntity<Page<userResponse>> getUsers(@RequestHeader(value = "X-User-Role", required = true) String role,
                                                       Pageable pageable) {
        return ResponseEntity.ok()
                .body(userService.getUsers(role,pageable));
    }
    @GetMapping("{userId}")
    public ResponseEntity<userDetailResponse> getUser(@PathVariable UUID userId,
                                                      @RequestHeader(value = "X-User-Id", required = true) UUID id,
                                                      @RequestHeader(value = "X-User-Role", required = true) String role) {
        return ResponseEntity.ok()
                .body(userService.getUser(userId,id,role));
    }
    @PutMapping("{userId}")
    public ResponseEntity<userDetailResponse> updateRole(@PathVariable UUID userId,
                                                      @RequestBody updateRoleRequest updateRoleRequest,
                                                      @RequestHeader(value = "X-User-Role", required = true) String role) {
        return ResponseEntity.ok()
                .body(userService.updateRole(userId,role,updateRoleRequest));
    }
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID userId,
                                             @RequestHeader(value = "X-User-Id", required = true) UUID id,
                                             @RequestHeader(value = "X-User-Role", required = true) String role) {
        return ResponseEntity.ok()
                .body(userService.deleteUser(userId,id,role));
    }
    @GetMapping("search") // 유저 네임으로 검색
    public ResponseEntity<Page<userResponse>> searchUser(@RequestHeader(value = "X-User-Role", required = true) String role,
                                                         @RequestParam("username") String username,
                                                         Pageable pageable) {
        return ResponseEntity.ok()
                .body(userService.searchUser(role,username,pageable));
    }
}
