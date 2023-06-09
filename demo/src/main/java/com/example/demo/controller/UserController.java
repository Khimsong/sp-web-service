package com.example.demo.controller;

import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserRequestWithParameterDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.model.HttpResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User save(@RequestBody UserRequestDTO userRequestDTO)
    {
        return userService.save(userRequestDTO);
    }
    @PostMapping("/{id}")
    public User save(@RequestBody UserRequestWithParameterDTO userRequestWithParameterDTO, @PathVariable("id") Long id)
    {
        return userService.save(userRequestWithParameterDTO,id);
    }
    @PutMapping("{userId}/department/{depId}")
    public User update(@RequestBody UserRequestWithParameterDTO userRequestWithParameterDTO, @PathVariable("userId") Long userId,@PathVariable("depId") Long depId)
    {
        return userService.update(userRequestWithParameterDTO,userId,depId);
    }
    @PutMapping("{id}")
    public User update(@RequestBody UserRequestDTO userRequestDTO, @PathVariable Long id)
    {
        return userService.update(userRequestDTO,id);
    }
    @GetMapping
    public ResponseEntity<HttpResponse> findAll()
    {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User Retrieved")
                        .status(HttpStatus.NO_CONTENT)
                        .statusCode(HttpStatus.OK.value())
                        .data(Collections.singletonMap("users",userService.findAll()))
                .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpResponse> delete(@PathVariable Long id)
    {
        userService.delete(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .message("User id : " + id +" was successfully deleted.")
                        .status(HttpStatus.NO_CONTENT)
                        .statusCode(HttpStatus.NO_CONTENT.value())
                        .build()

        );
    }

}
