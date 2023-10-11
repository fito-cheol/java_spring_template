package com.rest.api.entity;

import com.rest.api.dto.StadiumDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="stadium")
@Getter @Setter
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

    public static Stadium toStadium(StadiumDTO stadiumDTO){
        return new Stadium(stadiumDTO.getId(), stadiumDTO.getName(), stadiumDTO.getNickName(), stadiumDTO.getAddress());
    }
}
