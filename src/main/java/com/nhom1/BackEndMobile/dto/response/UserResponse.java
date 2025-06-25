package com.nhom1.BackEndMobile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String password;
    String role;
    String avatar_url;
    String fullname;
    String status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
