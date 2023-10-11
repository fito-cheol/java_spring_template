// Generated with g9.

package com.rest.api.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.rest.api.dto.ScheduleDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="schedule")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, name="schedule_id")
    private Long id;
    @Column(nullable=false)
    private LocalDate date;
    @Column(nullable=false)
    private int type;
    @Column(name="start_time")
    private LocalTime startTime;
    @Column(name="end_time")
    private LocalTime endTime;
    @Column(length=100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // EAGER
    @JoinColumn(name="stadium_id")
    private Stadium stadium;

    public static Schedule toSchedule(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setType(scheduleDTO.getType());
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());
        schedule.setName(scheduleDTO.getName());

        return schedule;
    }
    public Schedule updateScheduleExceptId(Schedule schedule){

        this.setDate(schedule.getDate());
        this.setType(schedule.getType());
        this.setStartTime(schedule.getStartTime());
        this.setEndTime(schedule.getEndTime());
        this.setName(schedule.getName());

        return this;
    }
}
