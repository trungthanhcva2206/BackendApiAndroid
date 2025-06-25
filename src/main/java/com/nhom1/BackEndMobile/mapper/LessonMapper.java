package com.nhom1.BackEndMobile.mapper;

import com.nhom1.BackEndMobile.dto.request.LessonRequest;
import com.nhom1.BackEndMobile.dto.response.LessonResponse;
import com.nhom1.BackEndMobile.entity.Course;
import com.nhom1.BackEndMobile.entity.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toLesson(LessonRequest request, Course course) {
        return Lesson.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .course(course)
                .build();
    }

    public LessonResponse toLessonResponse(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .courseId(lesson.getCourse().getId())
                .createdAt(lesson.getCreatedAt())
                .updatedAt(lesson.getUpdatedAt())
                .build();
    }
}
