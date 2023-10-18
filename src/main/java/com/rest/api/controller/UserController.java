package com.rest.api.controller;

import com.rest.api.util.CustomApiResponse;
import com.rest.api.dto.UserDTO;
import com.rest.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "Sample API User")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(
            description = "로그인",
            summary = "이메일, 비번 입력",
            responses = {
                @ApiResponse(
                        description = "Success",
                        responseCode = "200"
                )
            }
    )
    @PostMapping("/login")
    public CustomApiResponse<String> login(@RequestBody UserDTO userDTO){
        String jwt = userService.login(userDTO);

        if (jwt != null){
            return CustomApiResponse.createSuccess(jwt);
        }else{
            String failMessage = "Failed with email: " + userDTO.getEmail();
            return (CustomApiResponse<String>) CustomApiResponse.createError("토큰을 만들지 못했습니다");
        }
    }
    @PostMapping("/register")
    public CustomApiResponse<String> save(@ModelAttribute UserDTO userDTO, Authentication authentication){
        userService.addUser(userDTO);
        return CustomApiResponse.createSuccess("userDTO =" + userDTO + "\n 유저 인증 이메일 정보:" + authentication.getName());
    }


}
