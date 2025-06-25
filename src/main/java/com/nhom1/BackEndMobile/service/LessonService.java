package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.LessonRequest;
import com.nhom1.BackEndMobile.dto.response.LessonResponse;
import com.nhom1.BackEndMobile.entity.Course;
import com.nhom1.BackEndMobile.entity.Lesson;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.mapper.LessonMapper;
import com.nhom1.BackEndMobile.repository.CourseRepository;
import com.nhom1.BackEndMobile.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LessonService {
    LessonRepository lessonRepository;
    CourseRepository courseRepository;
    LessonMapper lessonMapper;

    public LessonResponse createLesson(LessonRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        Lesson lesson = lessonMapper.toLesson(request, course);
        return lessonMapper.toLessonResponse(lessonRepository.save(lesson));
    }

    public List<LessonResponse> getAllLessons() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::toLessonResponse)
                .collect(Collectors.toList());
    }

    public List<LessonResponse> getLessonsByCourseId(String courseId) {
        return lessonRepository.findByCourseId(courseId).stream()
                .map(lessonMapper::toLessonResponse)
                .collect(Collectors.toList());
    }

    public LessonResponse getLessonById(String id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.LESSON_NOT_FOUND));
        return lessonMapper.toLessonResponse(lesson);
    }

    public void deleteLesson(String id) {
        lessonRepository.deleteById(id);
    }
}
