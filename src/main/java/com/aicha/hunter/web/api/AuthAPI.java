package com.aicha.hunter.web.api;

import com.aicha.hunter.config.JwtService;
import com.aicha.hunter.domain.entity.User;
import com.aicha.hunter.service.UserService;
import com.aicha.hunter.web.vm.mapper.UserVmMapper;
import com.aicha.hunter.web.vm.request.LoginRequest;
import com.aicha.hunter.web.vm.request.RegisterRequest;
import com.aicha.hunter.web.vm.response.AuthResponse;
import com.aicha.hunter.web.vm.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthAPI {

    private final UserService userService;
    private final UserVmMapper userVmMapper;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {
        User userEntity = userVmMapper.toUser(loginRequest);
        User user = userService.login(userEntity);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token, userVmMapper.toUserResponse(user)));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest) {
        User userEntity = userVmMapper.toUser(registerRequest);
        User user = userService.addSUser(userEntity);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token, userVmMapper.toUserResponse(user)));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }
}