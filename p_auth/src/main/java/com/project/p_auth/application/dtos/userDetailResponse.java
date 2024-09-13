package com.project.p_auth.application.dtos;

import com.project.p_auth.domain.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class userDetailResponse {
    private UUID userId;
    private String username;
    private UserRoleEnum role;
    private String slackId;
    private String phone;
    private String email;
    public  userDetailResponse(UUID userId , String username , UserRoleEnum role , String slackId , String phone , String email){
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.slackId  =slackId;
        this.phone = phone;
        this.email =  email;
    }
}
