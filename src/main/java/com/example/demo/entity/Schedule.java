// Generated with g9.

package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name="schedule")
@Getter
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, name="schedule_id")
    private int id;
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

    @ManyToOne(fetch = FetchType.LAZY) // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
    @JoinColumn(name="stadium_id") // foreign key (userId) references User (id)
    private Stadium stadium; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다. //참조 할 테이블

}
