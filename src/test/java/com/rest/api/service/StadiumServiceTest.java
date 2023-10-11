package com.rest.api.service;

import com.rest.api.dto.StadiumDTO;
import com.rest.api.entity.Stadium;
import com.rest.api.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
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
        Optional<Stadium> oneStadium = stadiumRepository.findById(stadiumId);
        if (oneStadium.isEmpty()){
            fail("추가된 Stadium을 찾지 못함");
        }
        assertEquals(oneStadium.get().getName(), stadiumDTO.getName());
    }

    @Test
    void updateNickname() {
        StadiumDTO stadiumDTO = new StadiumDTO(null, "상암 월드컵 경기장", "풋살장1", "515 Seongsan-dong, Mapo-gu, Seoul");
        Long stadiumId = stadiumService.add(stadiumDTO);
        String newNick = "새로운 닉네임";
        stadiumService.updateNickname(stadiumId, newNick);

        Optional<Stadium> byId = stadiumRepository.findById(stadiumId);
        if (byId.isEmpty()){
            fail("해당하는 Stadium을 찾지 못했습니다");
        }
        assertEquals(byId.get().getNickName(), newNick);
    }

    @Test
    void delete() {
        StadiumDTO stadiumDTO = new StadiumDTO(null, "상암 월드컵 경기장", "풋살장1", "515 Seongsan-dong, Mapo-gu, Seoul");
        Long stadiumId = stadiumService.add(stadiumDTO);

        stadiumService.delete(stadiumId);
        Optional<Stadium> byId = stadiumRepository.findById(stadiumId);
        assert(byId.isEmpty());
    }
}