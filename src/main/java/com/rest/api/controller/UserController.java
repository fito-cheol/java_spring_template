package com.rest.api.controller;

import com.rest.api.util.ApiResponse;
import com.rest.api.dto.UserDTO;
import com.rest.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public ApiResponse<String> save(@ModelAttribute UserDTO userDTO, Authentication authentication){
        userService.addUser(userDTO);
        return ApiResponse.createSuccess("userDTO =" + userDTO + "\n 유저 인증 이메일 정보:" + authentication.getName());
    }

    @PostMapping("/user/login")
    public ApiResponse<String> login(@RequestBody UserDTO userDTO){
        String jwt = userService.login(userDTO);

        if (jwt != null){
            return ApiResponse.createSuccess(jwt);
        }else{
            String failMessage = "Failed with email: " + userDTO.getEmail();
            return (ApiResponse<String>) ApiResponse.createError("토큰을 만들지 못했습니다");
        }
    }
}
