package com.nhom1.BackEndMobile.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class userUpdateRequest {
    String password;
    String role;
    String avatar_url;
    String fullname;
    String status;
}
