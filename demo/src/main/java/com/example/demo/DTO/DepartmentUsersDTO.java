package com.example.demo.DTO;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentUsersDTO {

    @JsonProperty(value = "department_id")
    private Long id;
    @JsonProperty(value = "department_name")
    private String name;
    private List<UserResponseDTO> users;

}
