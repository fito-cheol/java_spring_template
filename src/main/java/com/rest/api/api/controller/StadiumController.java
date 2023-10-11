package com.rest.api.api.controller;

import com.rest.api.api.ApiResponse;
import com.rest.api.dto.StadiumDTO;
import com.rest.api.service.StadiumService;
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
