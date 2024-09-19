package com.project.p_auth.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class userResponse {
    private UUID userId;
    private String username;
    public userResponse(UUID userId, String username){
        this.userId = userId;
        this.username = username;
    }
}
