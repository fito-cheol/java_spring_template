package com.rest.api.controller;

import com.rest.api.util.CustomApiResponse;
import com.rest.api.dto.StadiumDTO;
import com.rest.api.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stadium")
public class StadiumController {
    private final StadiumService stadiumService;

    @GetMapping("/all")
    public CustomApiResponse<List<StadiumDTO>> all(){

        return CustomApiResponse.createSuccess(stadiumService.getAll());
    };
}
