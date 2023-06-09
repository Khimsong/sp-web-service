package com.example.demo.service;

import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserRequestWithParameterDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {

    User save(UserRequestDTO userRequestDTO);
    User save(UserRequestWithParameterDTO userRequestWithParameterDTO, Long id);
    List<UserResponseDTO> findAll();
    User update(UserRequestWithParameterDTO userRequestWithParameterDTO, Long userId,Long depId);
    User update(UserRequestDTO userRequestDTO,Long id);
    void delete(Long id);

}
