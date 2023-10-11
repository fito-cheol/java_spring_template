package com.rest.api.service;

import com.rest.api.dto.ScheduleDTO;
import com.rest.api.dto.StadiumDTO;
import com.rest.api.entity.Schedule;
import com.rest.api.entity.Stadium;
import com.rest.api.repository.ScheduleRepository;
import com.rest.api.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired  StadiumRepository stadiumRepository;
    @Autowired  ScheduleService scheduleService;
    @Test
    void findSchedule() {
        String scheduleName = "샘플_스케쥴";
        String stadiumName = "월드컵 경기장";
        Schedule savedSchedule = insertData(scheduleName, stadiumName);

        ScheduleDTO schedule = scheduleService.findSchedule(savedSchedule.getId());

        assertEquals(schedule.getName(), scheduleName);
    }

    @Test
    void findStadium() {
        String scheduleName = "샘플_스케쥴";
        String stadiumName = "월드컵 경기장";
        Schedule savedSchedule = insertData(scheduleName, stadiumName);

        StadiumDTO stadium = scheduleService.findStadium(savedSchedule.getId());

        assertEquals(stadium.getName(), stadiumName);
    }

    Schedule insertData(String scheduleName, String stadiumName){
        Stadium stadium = new Stadium(null, "월드컵 경기장","경기장1", "서울 특별시 상암");
        Stadium savedStadium = stadiumRepository.save(stadium);

        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(11, 0);

        Schedule sampleSchedule = new Schedule(null, LocalDate.now(), 1, startTime, endTime, scheduleName, null);
        sampleSchedule.setStadium(savedStadium);
        Schedule savedSchedule = scheduleRepository.save(sampleSchedule);
        return savedSchedule;
//        return new CombinedEntity(savedSchedule, savedStadium);
    }


}

//
//@Getter
//@Setter
//@AllArgsConstructor
//class CombinedEntity {
//    private Schedule schedule;
//    private Stadium stadium;
//}