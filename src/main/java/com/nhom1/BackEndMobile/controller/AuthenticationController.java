package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.AuthenticationRequest;
import com.nhom1.BackEndMobile.dto.response.ApiResponse;
import com.nhom1.BackEndMobile.dto.response.AuthenticationRespose;
import com.nhom1.BackEndMobile.entity.User;
import com.nhom1.BackEndMobile.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nhom1.BackEndMobile.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    UserRepository userRepository;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationRespose> authenticate(@RequestBody AuthenticationRequest request) {
        boolean result = authenticationService.authenticate(request);

        String status = null;
        String role = null;
        String id = null;
        if (result) {
            role = userRepository.findByUsername(request.getUsername())
                    .map(User::getRole)
                    .orElse(null);
            status = userRepository.findByUsername(request.getUsername())
                    .map(User::getStatus)
                    .orElse(null);
            id = userRepository.findByUsername(request.getUsername())
                    .map(User::getId)
                    .orElse(null);
        }

        return ApiResponse.<AuthenticationRespose>builder()
                .result(AuthenticationRespose.builder()
                        .authenticated(result)
                        .role(role)
                        .status(status)
                        .id(id)
                        .build())
                .build();
    }
}
