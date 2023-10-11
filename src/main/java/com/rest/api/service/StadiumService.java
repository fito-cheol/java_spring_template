package com.rest.api.service;

import com.rest.api.dto.StadiumDTO;
import com.rest.api.entity.Stadium;
import com.rest.api.repository.StadiumRepository;
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
    public Long add(StadiumDTO stadiumDTO) throws RuntimeException {
        if (checkDuplicate(stadiumDTO)){
            throw new RuntimeException("주소가 중복되었습니다. 주소: " + stadiumDTO.getAddress());
        }
        Stadium stadium = Stadium.toStadium(stadiumDTO);
        Stadium savedStadium = stadiumRepository.save(stadium);
        return savedStadium.getId();
    }
    private Boolean checkDuplicate(StadiumDTO stadiumDTO){
        Optional<Stadium> byAddress = stadiumRepository.findByAddress(stadiumDTO.getAddress());
        return byAddress.isEmpty();
    }
    public StadiumDTO findOne(Long stadiumId){
        Optional<Stadium> byId = stadiumRepository.findById(stadiumId);
        return byId.map(StadiumDTO::toStadiumDTO).orElse(null);
    }
    public void updateNickname(Long stadiumId, String nickname){
        Optional<Stadium> byId = stadiumRepository.findById(stadiumId);
        byId.ifPresent(stadium -> stadium.setNickName(nickname));
    }
    public void delete(Long stadiumId){
        stadiumRepository.deleteById(stadiumId);
    }
}
