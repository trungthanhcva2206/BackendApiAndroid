package com.nhom1.BackEndMobile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRespose {
    boolean authenticated;
    String role;
    String status;
    String id;
}
