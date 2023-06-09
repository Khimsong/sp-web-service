package com.example.demo.DTO;

import com.example.demo.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestWithParameterDTO {
    private String email;
    private String username;
    private String password;
    private Set<Role> roles;
}
