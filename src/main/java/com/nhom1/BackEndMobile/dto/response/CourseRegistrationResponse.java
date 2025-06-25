package com.nhom1.BackEndMobile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRegistrationResponse {
    String id;
    String userId;
    String courseId;
    LocalDateTime createdAt;
}
