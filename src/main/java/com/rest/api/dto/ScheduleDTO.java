package com.rest.api.dto;

import com.rest.api.entity.Schedule;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleDTO {
    private Long id;
    private LocalDate date;
    private int type;
    private LocalTime startTime;
    private LocalTime endTime;
    private String name;

    public static ScheduleDTO toScheduleDTO(Schedule schedule) {
        return new ScheduleDTO(schedule.getId(), schedule.getDate(), schedule.getType(), schedule.getStartTime(), schedule.getEndTime(), schedule.getName());
    }
}
