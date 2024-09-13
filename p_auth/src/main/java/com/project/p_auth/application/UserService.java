package com.project.p_auth.application;

import com.project.p_auth.application.dtos.updateRoleRequest;
import com.project.p_auth.application.dtos.userDetailResponse;
import com.project.p_auth.application.dtos.userResponse;
import com.project.p_auth.domain.User;
import com.project.p_auth.domain.UserRepository;
import com.project.p_auth.domain.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<userResponse> getUsers(String role) {
        if(!role.equals("MASTER")){
            throw new IllegalArgumentException("권한없음");
        }
        ArrayList<userResponse> userResponseList = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            userResponseList.add(new userResponse(user.getUserId(),user.getUsername()));
        }
        return userResponseList;
    }


    public userDetailResponse getUser(UUID userId, UUID id, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NullPointerException("해당유저없음")
                );
        if(!role.equals("MASTER")){
            if(!check(user.getUserId(),id)){
                throw new IllegalArgumentException("권한없음");
            }
        }
        return new userDetailResponse(user.getUserId(),user.getUsername(),user.getRole(),user.getSlackId(),user.getPhone(),user.getEmail());
    }
    @Transactional
    public userDetailResponse updateRole(UUID userId, String role, updateRoleRequest updateRoleRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NullPointerException("해당유저없음")
                );
        if(!role.equals("MASTER")){
            throw new IllegalArgumentException("권한없음");
        }
        UserRoleEnum userRoleEnum=UserRoleEnum.valueOf(updateRoleRequest.getUpdateRole());
        user.updateRole(userRoleEnum);
        userRepository.save(user);
        return new userDetailResponse(user.getUserId(),user.getUsername(),user.getRole(),user.getSlackId(),user.getPhone(),user.getEmail());
    }
    public String deleteUser(UUID userId, UUID id, String role) {
        return null;
    }

    public Page<userResponse> searchUser(String role) {
        return null;
    }

    private boolean check(UUID userId, UUID id) {
        if(!userId.equals(id)){
            return false;
        }
        return true;
    }


}
