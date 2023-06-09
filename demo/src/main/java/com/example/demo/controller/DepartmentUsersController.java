package com.example.demo.controller;

import com.example.demo.DTO.DepartmentUsersDTO;
import com.example.demo.model.HttpResponse;
import com.example.demo.service.DepartmentUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/department/users")
public class DepartmentUsersController {

    private final DepartmentUsersService departmentUsersService;

    @GetMapping
    public ResponseEntity<HttpResponse> departmentUsersDTO()
    {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User Retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Collections.singletonMap("responseData",departmentUsersService.findAllDepartmentUsers()))
                        .build());
    }

}
