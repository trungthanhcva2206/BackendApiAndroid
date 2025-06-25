package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.CourseRegistrationRequest;
import com.nhom1.BackEndMobile.dto.response.CourseRegistrationResponse;
import com.nhom1.BackEndMobile.service.CourseRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/course-registrations")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CourseRegistrationController {

    CourseRegistrationService courseRegistrationService;

    @PostMapping
    public CourseRegistrationResponse register(@RequestBody @Valid CourseRegistrationRequest request) {
        return courseRegistrationService.register(request);
    }

    @GetMapping
    public List<CourseRegistrationResponse> getAll() {
        return courseRegistrationService.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<CourseRegistrationResponse> getByUser(@PathVariable String userId) {
        return courseRegistrationService.getByUserId(userId);
    }

    @GetMapping("/course/{courseId}")
    public List<CourseRegistrationResponse> getByCourse(@PathVariable String courseId) {
        return courseRegistrationService.getByCourseId(courseId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        courseRegistrationService.delete(id);
        return "Đã hủy đăng ký khóa học";
    }
}

