package com.example.demo.service;

import com.example.demo.dto.StadiumDTO;
import com.example.demo.entity.Stadium;
import com.example.demo.repository.StadiumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Long add(StadiumDTO stadiumDTO){
        Stadium stadium = Stadium.toStadium(stadiumDTO);
        Stadium savedStadium = stadiumRepository.save(stadium);
        return savedStadium.getId();
        // TODO: 중복된 경우 처리
    }
    public StadiumDTO findOne(Long stadiumId){
        Optional<Stadium> byId = stadiumRepository.findById(stadiumId);
        return byId.map(StadiumDTO::toStadiumDTO).orElse(null);
    }
}
