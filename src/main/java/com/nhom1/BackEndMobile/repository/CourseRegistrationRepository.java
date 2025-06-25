package com.nhom1.BackEndMobile.repository;

import com.nhom1.BackEndMobile.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, String> {
    List<CourseRegistration> findByUserId(String userId);
    List<CourseRegistration> findByCourseId(String courseId);
    boolean existsByUserIdAndCourseId(String userId, String courseId);
}
