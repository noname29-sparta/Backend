package com.project.p_auth.application;

import com.project.p_auth.application.dtos.AuthResponse;
import com.project.p_auth.application.dtos.LoginRequest;
import com.project.p_auth.application.dtos.SignUpRequest;
import com.project.p_auth.domain.User;
import com.project.p_auth.domain.UserRepository;
import com.project.p_auth.domain.UserRoleEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;
    private final SecretKey secretKey;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository,
                       @Value("${service.jwt.secret-key}") String secretKey , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.passwordEncoder = passwordEncoder;
    }
    public String companySignUp(SignUpRequest signUpRequest) {
        isNameOrEmail(signUpRequest.getUsername(),signUpRequest.getEmail());
                userRepository.save(User.create(signUpRequest.getUsername(),passwordEncoder.encode(signUpRequest.getPassword()),
                        UserRoleEnum.COMPANY,signUpRequest.getSlack_id(),signUpRequest.getPhone(),signUpRequest.getEmail()));
        return "회원가입성공";
    }
    public String hubSignUp(SignUpRequest signUpRequest) {
        isNameOrEmail(signUpRequest.getUsername(),signUpRequest.getEmail());
                userRepository.save(User.create(signUpRequest.getUsername(),passwordEncoder.encode(signUpRequest.getPassword()),
                        UserRoleEnum.HUB,signUpRequest.getSlack_id(),signUpRequest.getPhone(),signUpRequest.getEmail()));
        return "회원가입성공";
    }

    public String deliverySignUp(SignUpRequest signUpRequest) {
        isNameOrEmail(signUpRequest.getUsername(),signUpRequest.getEmail());
        userRepository.save(User.create(signUpRequest.getUsername(),passwordEncoder.encode(signUpRequest.getPassword()),
                UserRoleEnum.DELIVERY,signUpRequest.getSlack_id(),signUpRequest.getPhone(),signUpRequest.getEmail()));
        return "회원가입성공";
    }

    public String masterSignUp(SignUpRequest signUpRequest) {
        isNameOrEmail(signUpRequest.getUsername(),signUpRequest.getEmail());
        userRepository.save(User.create(signUpRequest.getUsername(),passwordEncoder.encode(signUpRequest.getPassword()),
                UserRoleEnum.MASTER,signUpRequest.getSlack_id(),signUpRequest.getPhone(),signUpRequest.getEmail()));
        return "회원가입성공";
    }

    public AuthResponse signIn(LoginRequest loginRequest) {
        User user =userRepository.findByusername(loginRequest.getUsername())
                .orElseThrow(()->new IllegalArgumentException("Invalid user ID or password"));
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 다름");
        }
        return createToken(user.getUserId(),user.getRole());

    }
    public AuthResponse createToken(UUID userId, UserRoleEnum role) {
        return AuthResponse.of(Jwts.builder()
                .claim("user_id", userId)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact());
    }
    private void isNameOrEmail(String username , String email){
        Optional<User> userName = userRepository.findByusername(username);
        if(userName.isPresent()){
            throw new IllegalArgumentException("이미 해당 이름이 존재함");
        }
        Optional<User> userEmail = userRepository.findByemail(email);
        if(userEmail.isPresent()){
            throw new IllegalArgumentException("이미 해당 이메일 존재함");
        }
    }

    public Boolean verifyUser(final String userId) {
        // userId 로 User 를 조회 후 isPresent() 로 존재유무를 리턴함
        return userRepository.findById(UUID.fromString(userId)).isPresent();
    }
}
