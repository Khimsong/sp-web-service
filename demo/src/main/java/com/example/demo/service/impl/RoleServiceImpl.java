package com.example.demo.service.impl;

import com.example.demo.exception.NoSuchElementFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role roleRequest, Long id) {
        Role role = roleRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("Role not found with id:" +id)
                );
        role.setRoleName(roleRequest.getRoleName());
       return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("Role not found with id:" +id)
                );
        roleRepository.delete(role);
    }
}
