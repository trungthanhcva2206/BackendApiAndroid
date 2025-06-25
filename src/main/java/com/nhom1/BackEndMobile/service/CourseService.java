package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.CourseRequest;
import com.nhom1.BackEndMobile.dto.response.CourseResponse;
import com.nhom1.BackEndMobile.entity.Course;
import com.nhom1.BackEndMobile.entity.Department;
import com.nhom1.BackEndMobile.entity.User;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.mapper.CourseMapper;
import com.nhom1.BackEndMobile.repository.CourseRepository;
import com.nhom1.BackEndMobile.repository.DepartmentRepository;
import com.nhom1.BackEndMobile.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CourseService {
    CourseRepository courseRepository;
    DepartmentRepository departmentRepository;
    UserRepository userRepository;
    CourseMapper courseMapper;

    public CourseResponse createCourse(CourseRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        User teacher = userRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        Course course = courseMapper.toCourse(request, department, teacher);
        return courseMapper.toCourseResponse(courseRepository.save(course));
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toCourseResponse)
                .toList();
    }

    public CourseResponse getCourse(String id) {
        return courseRepository.findById(id)
                .map(courseMapper::toCourseResponse)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
    }

    public CourseResponse updateCourse(String id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        User teacher = userRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setThumbnailUrl(request.getThumbnailUrl());
        course.setStatus(request.getStatus());
        course.setDepartment(department);
        course.setTeacher(teacher);

        return courseMapper.toCourseResponse(courseRepository.save(course));
    }

    public void deleteCourse(String id) {
        if (!courseRepository.existsById(id)) {
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        courseRepository.deleteById(id);
    }
}