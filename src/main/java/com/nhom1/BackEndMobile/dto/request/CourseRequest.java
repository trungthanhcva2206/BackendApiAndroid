package com.nhom1.BackEndMobile.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {
    @NotBlank
    String title;
    String description;
    String thumbnailUrl;
    String status;
    String departmentId;
    String teacherId;
}
