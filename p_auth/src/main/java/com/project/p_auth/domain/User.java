package com.project.p_auth.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "p_user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private UserRoleEnum role;

    @Column(name = "slack_id")
    private String slackId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public static User create(String username , String password , UserRoleEnum role , String slackId , String phone , String email) {
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .slackId(slackId)
                .phone(phone)
                .email(email)
                .build();
    }
    public void updateRole(UserRoleEnum userRoleEnum){
        this.role = userRoleEnum;
    }
}
