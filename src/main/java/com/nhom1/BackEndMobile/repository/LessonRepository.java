package com.nhom1.BackEndMobile.repository;

import com.nhom1.BackEndMobile.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, String> {
    List<Lesson> findByCourseId(String courseId);
}
