package com.example.demo.api.controller;

import com.example.demo.api.ApiResponse;
import com.example.demo.dto.StadiumDTO;
import com.example.demo.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StadiumController {
    private final StadiumService stadiumService;

    @GetMapping("/stadium/all")
    public ApiResponse<List<StadiumDTO>> all(){

        return ApiResponse.createSuccess(stadiumService.getAll());
    };
}
