package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.LessonRequest;
import com.nhom1.BackEndMobile.dto.response.LessonResponse;
import com.nhom1.BackEndMobile.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LessonController {
    LessonService lessonService;

    @PostMapping
    LessonResponse create(@RequestBody LessonRequest request) {
        return lessonService.createLesson(request);
    }

    @GetMapping
    List<LessonResponse> getAll() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/course/{courseId}")
    List<LessonResponse> getByCourse(@PathVariable String courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }

    @GetMapping("/{id}")
    LessonResponse getOne(@PathVariable String id) {
        return lessonService.getLessonById(id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        lessonService.deleteLesson(id);
    }
}

