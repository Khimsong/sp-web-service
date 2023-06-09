package com.example.demo.service.impl;

import com.example.demo.DTO.DepartmentUsersDTO;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentUsersService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentUsersServiceImpl implements DepartmentUsersService {

    private final ModelMapper modelMapper;
    private  final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentUsersDTO> findAllDepartmentUsers() {
        return departmentRepository
                .findAll()
                .stream()
                .map(this::convertToDepartmentUsersDtoWithModelMapper)
                .collect(Collectors.toList());
    }


    private DepartmentUsersDTO convertToDepartmentUsersDtoWithModelMapper(Department department)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        DepartmentUsersDTO departmentUsersDTO = modelMapper.map(department,DepartmentUsersDTO.class);
        return departmentUsersDTO;
    }

}
