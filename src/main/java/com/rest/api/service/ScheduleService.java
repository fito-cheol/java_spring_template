package com.rest.api.service;

import com.rest.api.dto.ScheduleDTO;
import com.rest.api.dto.StadiumDTO;
import com.rest.api.entity.Schedule;
import com.rest.api.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public Long add(ScheduleDTO scheduleDTO){
        Schedule schedule = Schedule.toSchedule(scheduleDTO);
        Schedule save = scheduleRepository.save(schedule);
        return save.getId();
    }
    public List<ScheduleDTO> getAll(){
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        for (Schedule schedule :scheduleRepository.findAll()){
            ScheduleDTO scheduleDTO = ScheduleDTO.toScheduleDTO(schedule);
            scheduleDTOs.add(scheduleDTO);
        }
        return scheduleDTOs;
    }

    public ScheduleDTO findSchedule(Long scheduleId){
        Optional<Schedule> byId = scheduleRepository.findById(scheduleId);
        return byId.map(ScheduleDTO::toScheduleDTO).orElse(null);
    }

    public StadiumDTO findStadium(Long scheduleId){
        Optional<Schedule> byId = scheduleRepository.findById(scheduleId);
        if (byId.isEmpty()){
            return null;
        }
        return StadiumDTO.toStadiumDTO(byId.get().getStadium());
    }

    public void delete(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }

}
