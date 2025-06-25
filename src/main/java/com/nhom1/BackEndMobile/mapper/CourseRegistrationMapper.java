package com.nhom1.BackEndMobile.mapper;

import com.nhom1.BackEndMobile.dto.response.CourseRegistrationResponse;
import com.nhom1.BackEndMobile.entity.CourseRegistration;
import org.springframework.stereotype.Component;

@Component
public class CourseRegistrationMapper {
    public CourseRegistrationResponse toResponse(CourseRegistration registration) {
        return CourseRegistrationResponse.builder()
                .id(registration.getId())
                .userId(registration.getUser().getId())
                .courseId(registration.getCourse().getId())
                .createdAt(registration.getCreatedAt())
                .build();
    }
}
