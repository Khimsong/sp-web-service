package com.example.demo.DTO;

import com.example.demo.model.Department;
import com.example.demo.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Department department;
    private List<Role> roles;

}
