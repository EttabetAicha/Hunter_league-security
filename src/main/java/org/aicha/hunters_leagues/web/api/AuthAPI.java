package org.aicha.hunters_leagues.web.api;

import org.aicha.hunters_leagues.domain.User;
import org.aicha.hunters_leagues.service.UserService;
import org.aicha.hunters_leagues.web.vm.mapper.UserVmMapper;
import org.aicha.hunters_leagues.web.vm.request.LoginRequest;
import org.aicha.hunters_leagues.web.vm.request.RegisterRequest;
import org.aicha.hunters_leagues.web.vm.response.AuthResponse;
import org.aicha.hunters_leagues.web.vm.response.UserResponse;
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
    // method to login user
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(AuthResponse.builder().token(user.getToken()).build());
    }

    // method to register user
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest) {
        User userEntity = userVmMapper.toUser(registerRequest);
        User user = userService.addSUser(userEntity);
        return ResponseEntity.ok(AuthResponse.builder().token(user.getToken()).build());
    }
}