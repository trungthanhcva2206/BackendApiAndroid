package com.nhom1.BackEndMobile.controller;

import com.nhom1.BackEndMobile.dto.request.DepartmentRequest;
import com.nhom1.BackEndMobile.dto.response.DepartmentResponse;
import com.nhom1.BackEndMobile.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {

    DepartmentService departmentService;

    @PostMapping
    public DepartmentResponse create(@RequestBody DepartmentRequest request) {
        return departmentService.create(request);
    }

    @GetMapping
    public List<DepartmentResponse> getAll() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public DepartmentResponse getById(@PathVariable String id) {
        return departmentService.getById(id);
    }

    @PutMapping("/{id}")
    public DepartmentResponse update(@PathVariable String id, @RequestBody DepartmentRequest request) {
        return departmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        departmentService.delete(id);
        return "Department deleted successfully";
    }
}

