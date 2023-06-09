package com.example.demo.service.impl;

import com.example.demo.exception.NoSuchElementFoundException;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department, Long id) {
        Department updateDepartmentById = departmentRepository.
                findById(id)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("Department not found with id:" +id)
                );
        updateDepartmentById.setName(department.getName());
        return  departmentRepository.save(updateDepartmentById);
    }

//    @Override
//    public void delete(Long id) {
//        departmentRepository.delete(id);
//
//    }


}
