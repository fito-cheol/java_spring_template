package com.example.demo.api.controller;

import com.example.demo.api.model.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public String save(@ModelAttribute UserDTO userDTO){
        System.out.println("userDTO = " + userDTO);
        userService.addUser(userDTO);
        return "userDTO =" + userDTO;
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO){
        UserDTO loginResult = userService.login(userDTO);
        if (loginResult != null){
            return "Success";
        }else{
            return "Failed";
        }
    }
}
