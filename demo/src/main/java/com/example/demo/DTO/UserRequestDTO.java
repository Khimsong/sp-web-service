package com.example.demo.DTO;

import com.example.demo.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String email;
    private String username;
    private String password;
    @JsonProperty(value = "department_id")
    private Long departmentId;
    private List<Role> roles;
}
