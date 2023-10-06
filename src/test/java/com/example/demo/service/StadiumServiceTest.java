package com.example.demo.service;

import com.example.demo.dto.StadiumDTO;
import com.example.demo.entity.Stadium;
import com.example.demo.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StadiumServiceTest {

    @Autowired
    StadiumRepository stadiumRepository;

    @Autowired
    StadiumService stadiumService;

    @Test
    void getAll() {
        int initialSize = stadiumService.getAll().size();

        StadiumDTO stadiumDTO = new StadiumDTO(null, "상암 월드컵 경기장", "풋살장1", "515 Seongsan-dong, Mapo-gu, Seoul");
        stadiumService.add(stadiumDTO);

        StadiumDTO stadiumDTO2 = new StadiumDTO(null, "상암 월드컵 경기장2", "풋살장2", "516 Seongsan-dong, Mapo-gu, Seoul");
        stadiumService.add(stadiumDTO2);

        int laterSize = stadiumService.getAll().size();
        assert(initialSize+2 == laterSize);
    }
    @Test
    void add(){
        String name = "상암 월드컵 경기장";
        String nickname ="풋살장1";
        String address = "515 Seongsan-dong, Mapo-gu, Seoul";

        StadiumDTO stadiumDTO = new StadiumDTO(null, name, nickname, address);
        Long stadiumId = stadiumService.add(stadiumDTO);
        StadiumDTO oneStadium = stadiumService.findOne(stadiumId);
        assert(oneStadium.getName().equals(stadiumDTO.getName()));
    }
}