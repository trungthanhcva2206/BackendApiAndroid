package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.CourseRequest;
import com.nhom1.BackEndMobile.dto.response.ApiResponse;
import com.nhom1.BackEndMobile.dto.response.CourseResponse;
import com.nhom1.BackEndMobile.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CourseController {

    CourseService courseService;

    @PostMapping
    ApiResponse<CourseResponse> create(@RequestBody @Valid CourseRequest request) {
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.createCourse(request))
                .build();
    }

    @GetMapping
    List<CourseResponse> getAll() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    CourseResponse get(@PathVariable String id) {
        return courseService.getCourse(id);
    }

    @PutMapping("/{id}")
    CourseResponse update(@PathVariable String id, @RequestBody CourseRequest request) {
        return courseService.updateCourse(id, request);
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable String id) {
        courseService.deleteCourse(id);
        return "Course deleted successfully";
    }
}
