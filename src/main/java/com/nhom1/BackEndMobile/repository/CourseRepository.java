package com.nhom1.BackEndMobile.repository;

import com.nhom1.BackEndMobile.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {}
