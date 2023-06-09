package com.example.demo.service.impl;

import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserRequestWithParameterDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.exception.NoSuchElementFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;

    @Override
    public User save(UserRequestDTO userRequestDTO) {
        Department department = departmentRepository
                .findById(userRequestDTO.getDepartmentId())
                .orElseThrow(
                        () -> new NoSuchElementFoundException("Department not found with id:" +userRequestDTO.getDepartmentId())
                );

        User user = new User();
        user.setDepartment(department);
        user.setEmail(userRequestDTO.getEmail());
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword());
        user.getRoles().addAll(
                userRequestDTO.getRoles().stream().map(v->{
                    Role role = roleRepository.findById(v.getId()).orElseThrow(
                            () ->  new NoSuchElementFoundException("Role not found with id:" +v.getId())
                    );
                    role.getUsers().add(user);
                    return role;
                }).collect(Collectors.toList())
        );

        return userRepository.save(user);
    }

    @Override
    public User save(UserRequestWithParameterDTO userRequestWithParameterDTO, Long depId) {
        User user = new User();
        Department department = departmentRepository
                .findById(depId)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("Department not found with id:" +depId)
                );
        user.setDepartment(department);
        user.setEmail(userRequestWithParameterDTO.getEmail());
        user.setUsername(userRequestWithParameterDTO.getUsername());
        user.setPassword(userRequestWithParameterDTO.getPassword());


        user.getRoles().addAll(
                userRequestWithParameterDTO.getRoles().stream().map(v->{
                    Role role = roleRepository.findById(v.getId()).orElseThrow(
                            () ->  new NoSuchElementFoundException("Role not found with id:" +v.getId())
                    );
                    role.getUsers().add(user);
                    return role;
                }).collect(Collectors.toList())
        );

        return userRepository.save(user);
    }

    @Override
    public List<UserResponseDTO> findAll()
    {
        return userRepository
                .findAll()
                .stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    @Override
    public User update(UserRequestWithParameterDTO userRequestWithParameterDTO, Long userId,Long depId) {
        User userRequest = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("User not found with id:" +userId)
                );
        Department department = departmentRepository
                .findById(depId)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("Department not found with id:" +depId)
                );

        userRequest.setDepartment(department);
        userRequest.setEmail(userRequestWithParameterDTO.getEmail());
        userRequest.setUsername(userRequestWithParameterDTO.getUsername());
        userRequest.setPassword(userRequestWithParameterDTO.getPassword());

        return userRepository.save(userRequest);
    }

    @Override
    public User update(UserRequestDTO userRequestDTO, Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("User not found with id:" +id)
                );
        Department department = departmentRepository.
                findById(userRequestDTO.getDepartmentId())
                .orElseThrow(
                        () -> new NoSuchElementFoundException("Department not found with id:" +userRequestDTO.getDepartmentId())
                );
        user.setDepartment(department);
        user.setEmail(userRequestDTO.getEmail());
        user.setUsername(userRequestDTO.getUsername());
        user.setPassword(userRequestDTO.getPassword());

        if(!userRequestDTO.getRoles().isEmpty())
        {
            user.getRoles().removeAll(
                    user.getRoles().stream().map(v->{
                        Role role = roleRepository.findById(v.getId()).orElseThrow(
                                () ->  new NoSuchElementFoundException("Role not found with id:" +v.getId())
                        );
                        role.getUsers().removeAll(user.getRoles());
                        return role;
                    }).collect(Collectors.toList())
            );
            userRepository.save(user);
            user.getRoles().addAll(
                    userRequestDTO.getRoles().stream().map(v->{
                        Role role = roleRepository.findById(v.getId()).orElseThrow(
                                () ->  new NoSuchElementFoundException("Role not found with id:" +v.getId())
                        );
                        role.getUsers().add(user);
                        return role;
                    }).collect(Collectors.toList())
            );
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(
                        () -> new NoSuchElementFoundException("User not found with id:" +id)
                );
        userRepository.delete(user);
    }
    private UserResponseDTO convertUserToDto(User user){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        UserResponseDTO userResponseDTO = modelMapper.map(user,UserResponseDTO.class);
        return userResponseDTO;
    }



}
