package com.example.demo.service;

import com.example.demo.model.Department;

import java.util.List;

public interface DepartmentService {
    Department findById(Long id);
    List<Department> findAll();
    Department save(Department department);
    Department update(Department department,Long id);
//    void delete(Long id);
}
