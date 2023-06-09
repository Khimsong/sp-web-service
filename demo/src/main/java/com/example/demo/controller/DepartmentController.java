package com.example.demo.controller;

import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public Department save(@RequestBody Department department)
    {
        return departmentService.save(department);
    }
    @GetMapping
    public List<Department> findAll() {
        return departmentService.findAll();
     }

    @PutMapping("{id}")
    public Department update(@RequestBody Department department,@PathVariable Long id)
    {
        return departmentService.update(department,id);
    }

//    @DeleteMapping("{id}")
//    public void delete(@PathVariable Long id)
//    {
//         departmentService.delete(id);
//    }

}
