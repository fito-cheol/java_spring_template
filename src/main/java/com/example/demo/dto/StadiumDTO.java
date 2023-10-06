package com.example.demo.dto;

import com.example.demo.entity.Stadium;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StadiumDTO {
    private Long id;
    private String name;
    private String nickName;
    private String address;

    public static StadiumDTO toStadiumDTO(Stadium stadium) {
        return new StadiumDTO(stadium.getId(), stadium.getName(), stadium.getNickName(), stadium.getAddress());
    }
}
