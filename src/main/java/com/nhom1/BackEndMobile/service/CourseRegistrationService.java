package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.CourseRegistrationRequest;
import com.nhom1.BackEndMobile.dto.response.CourseRegistrationResponse;
import com.nhom1.BackEndMobile.entity.Course;
import com.nhom1.BackEndMobile.entity.CourseRegistration;
import com.nhom1.BackEndMobile.entity.User;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.mapper.CourseRegistrationMapper;
import com.nhom1.BackEndMobile.repository.CourseRegistrationRepository;
import com.nhom1.BackEndMobile.repository.CourseRepository;
import com.nhom1.BackEndMobile.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CourseRegistrationService {
    CourseRegistrationRepository courseRegistrationRepository;
    CourseRepository courseRepository;
    UserRepository userRepository;
    CourseRegistrationMapper courseRegistrationMapper;

    public CourseRegistrationResponse register(CourseRegistrationRequest request) {
        if (courseRegistrationRepository.existsByUserIdAndCourseId(request.getUserId(), request.getCourseId())) {
            throw new AppException(ErrorCode.DUPLICATE_COURSE_REGISTRATION);
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));

        CourseRegistration registration = CourseRegistration.builder()
                .user(user)
                .course(course)
                .build();

        return courseRegistrationMapper.toResponse(courseRegistrationRepository.save(registration));
    }

    public List<CourseRegistrationResponse> getAll() {
        return courseRegistrationRepository.findAll().stream()
                .map(courseRegistrationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<CourseRegistrationResponse> getByUserId(String userId) {
        return courseRegistrationRepository.findByUserId(userId).stream()
                .map(courseRegistrationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<CourseRegistrationResponse> getByCourseId(String courseId) {
        return courseRegistrationRepository.findByCourseId(courseId).stream()
                .map(courseRegistrationMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        courseRegistrationRepository.deleteById(id);
    }
}
