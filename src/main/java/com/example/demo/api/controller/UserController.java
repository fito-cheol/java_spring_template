package com.example.demo.api.controller;

import com.example.demo.api.ApiResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public ApiResponse<String> save(@ModelAttribute UserDTO userDTO){
        System.out.println("userDTO = " + userDTO);
        userService.addUser(userDTO);
        return ApiResponse.createSuccess("userDTO =" + userDTO);
    }

    @PostMapping("/user/login")
    public ApiResponse<String> login(@ModelAttribute UserDTO userDTO){
        UserDTO loginResult = userService.login(userDTO);
        if (loginResult != null){
            return ApiResponse.createSuccess("Success: " + loginResult.getUsername());
        }else{
            return ApiResponse.createSuccess("Failed: " + userDTO.getEmail());
        }
    }
}
