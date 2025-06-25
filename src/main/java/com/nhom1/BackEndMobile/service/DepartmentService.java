package com.nhom1.BackEndMobile.service;

import com.nhom1.BackEndMobile.dto.request.DepartmentRequest;
import com.nhom1.BackEndMobile.dto.response.DepartmentResponse;
import com.nhom1.BackEndMobile.entity.Department;
import com.nhom1.BackEndMobile.exception.AppException;
import com.nhom1.BackEndMobile.exception.ErrorCode;
import com.nhom1.BackEndMobile.mapper.DepartmentMapper;
import com.nhom1.BackEndMobile.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService {
    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    public DepartmentResponse create(DepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.DEPARTMENT_EXISTED);
        }
        Department department = departmentMapper.toEntity(request);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public List<DepartmentResponse> getAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public DepartmentResponse getById(String id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        return departmentMapper.toResponse(department);
    }

    public DepartmentResponse update(String id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND));
        departmentMapper.updateEntity(department, request);
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    public void delete(String id) {
        if (!departmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.DEPARTMENT_NOT_FOUND);
        }
        departmentRepository.deleteById(id);
    }
}
