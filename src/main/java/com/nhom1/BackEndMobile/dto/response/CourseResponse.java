package com.nhom1.BackEndMobile.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    String id;
    String title;
    String description;
    String thumbnailUrl;
    String status;
    String departmentId;
    String departmentName;
    String teacherId;
    String teacherName;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
