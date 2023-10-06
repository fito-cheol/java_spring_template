package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="stadium")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stadium {
    @Id
    @Column(name="stadium_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(nullable=false, length=60)
    private String name;
    @Column(name="nick_name", length=45)
    private String nickName;
    @Column(length=200)
    private String address;
}
