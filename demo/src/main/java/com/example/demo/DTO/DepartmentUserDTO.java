package com.example.demo.DTO;

import com.example.demo.model.Department;
import lombok.Data;

@Data
public class DepartmentUserDTO {

    private Long id;
    private String name;
    private String email;
    private Department department;

}
