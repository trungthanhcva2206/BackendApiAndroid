package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.userCreationRequest;
import com.nhom1.BackEndMobile.dto.request.userUpdateRequest;
import com.nhom1.BackEndMobile.dto.response.ApiResponse;
import com.nhom1.BackEndMobile.dto.response.UserResponse;
import com.nhom1.BackEndMobile.entity.User;
import com.nhom1.BackEndMobile.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid userCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userId, @RequestBody userUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

    @GetMapping("/role")
    List<UserResponse> getUsersByRole(@RequestParam("role") String role) {
        return userService.getUsersByRole(role);
    }

}
