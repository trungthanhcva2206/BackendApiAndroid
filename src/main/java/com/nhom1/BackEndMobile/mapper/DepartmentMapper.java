package com.nhom1.BackEndMobile.mapper;

import com.nhom1.BackEndMobile.dto.request.DepartmentRequest;
import com.nhom1.BackEndMobile.dto.response.DepartmentResponse;
import com.nhom1.BackEndMobile.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department toEntity(DepartmentRequest request) {
        return Department.builder()
                .name(request.getName())
                .build();
    }

    public DepartmentResponse toResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .createdAt(department.getCreatedAt())
                .updatedAt(department.getUpdatedAt())
                .build();
    }

    public void updateEntity(Department department, DepartmentRequest request) {
        department.setName(request.getName());
    }
}
