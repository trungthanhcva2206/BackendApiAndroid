package com.nhom1.BackEndMobile.repository;

import com.nhom1.BackEndMobile.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByName(String name);
}
