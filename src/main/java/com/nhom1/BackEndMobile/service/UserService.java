package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.userCreationRequest;
import com.nhom1.BackEndMobile.dto.request.userUpdateRequest;
import com.nhom1.BackEndMobile.dto.response.UserResponse;
import com.nhom1.BackEndMobile.entity.User;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.mapper.UserMapper;
import com.nhom1.BackEndMobile.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(userCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse updateUser(String userId, userUpdateRequest request){
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public List<UserResponse> getUsersByRole(String role) {
        List<User> users = userRepository.findAllByRole(role);
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }




}
