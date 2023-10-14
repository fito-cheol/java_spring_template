package com.rest.api.controller;

import com.rest.api.util.ApiResponse;
import com.rest.api.dto.UserDTO;
import com.rest.api.service.UserService;
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
        String jwt = userService.login(userDTO);
        if (jwt != null){
            return ApiResponse.createSuccess("Success with jwt: " + jwt);
        }else{
            return ApiResponse.createSuccess("Failed with email: " + userDTO.getEmail());
        }
    }
}
