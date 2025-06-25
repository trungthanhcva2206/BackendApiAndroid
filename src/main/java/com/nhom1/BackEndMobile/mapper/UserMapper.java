package com.nhom1.BackEndMobile.mapper;

import com.nhom1.BackEndMobile.dto.request.userCreationRequest;
import com.nhom1.BackEndMobile.dto.request.userUpdateRequest;
import com.nhom1.BackEndMobile.dto.response.UserResponse;
import com.nhom1.BackEndMobile.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(userCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, userUpdateRequest userUpdateRequest);
}
