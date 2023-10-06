package com.example.demo.service;

import com.example.demo.dto.StadiumDTO;
import com.example.demo.entity.Stadium;
import com.example.demo.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    public List<StadiumDTO> getAll() {
        List<StadiumDTO> stadiumDTOS = new ArrayList<>();

        for (Stadium stadium :stadiumRepository.findAll()){
            StadiumDTO stadiumDTO = StadiumDTO.toStadiumDTO(stadium);
            stadiumDTOS.add(stadiumDTO);
        }
        return stadiumDTOS;
    }
}
