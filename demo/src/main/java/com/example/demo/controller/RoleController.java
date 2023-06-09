package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<Role> findAll()
    {
     return roleService.findAll();
    }

    @PostMapping
    public Role save(@RequestBody Role role)
    {
        return roleService.save(role);
    }

    @PutMapping("{id}")
    public Role update(@RequestBody Role role,@PathVariable Long id)
    {
       return roleService.update(role, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id)
    {
        roleService.delete(id);
    }
}
