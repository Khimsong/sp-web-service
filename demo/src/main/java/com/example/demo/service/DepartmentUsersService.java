package com.example.demo.service;

import com.example.demo.DTO.DepartmentUsersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DepartmentUsersService {
     List<DepartmentUsersDTO> findAllDepartmentUsers();


}
