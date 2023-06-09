package com.example.demo.service;

import com.example.demo.model.Role;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role save(Role role);
    Role update(Role role,Long id);
    void delete(Long id);
}
