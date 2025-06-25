package com.nhom1.BackEndMobile.mapper;

import com.nhom1.BackEndMobile.dto.request.CourseRequest;
import com.nhom1.BackEndMobile.dto.response.CourseResponse;
import com.nhom1.BackEndMobile.entity.Course;
import com.nhom1.BackEndMobile.entity.Department;
import com.nhom1.BackEndMobile.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toCourse(CourseRequest request, Department department, User teacher) {
        return Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .thumbnailUrl(request.getThumbnailUrl())
                .status(request.getStatus())
                .department(department)
                .teacher(teacher)
                .build();
    }

    public CourseResponse toCourseResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .thumbnailUrl(course.getThumbnailUrl())
                .status(course.getStatus())
                .departmentId(course.getDepartment().getId())
                .departmentName(course.getDepartment().getName())
                .teacherId(course.getTeacher().getId())
                .teacherName(course.getTeacher().getFullname())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
