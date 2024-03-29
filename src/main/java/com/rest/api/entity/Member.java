// Generated with g9.

package com.rest.api.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.rest.api.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false, name="member_id")
    private Long id;
    @Column(nullable=false, length=45)
    private String name;
    @Column(length=45)
    private String position;
    @Column(name="uniform_number", length=30)
    private String uniformNumber;
    @Column(name="join_date", nullable=false)
    private LocalDate joinDate;
    @Column(name="nick_name", length=45)
    private String nickName;
    @Column(name="inflow_route")
    private int inflowRoute;
    @Column(name="bg_image", length=60)
    private String bgImage;
    private int grade;
    private int level;

    public static Member toMember(MemberDTO memberDTO){
        return new Member(memberDTO.getId(), memberDTO.getName(), memberDTO.getPosition(), memberDTO.getUniformNumber(),
                memberDTO.getJoinDate(), memberDTO.getNickName(), memberDTO.getInflowRoute(), memberDTO.getBgImage(),
                memberDTO.getGrade(), memberDTO.getLevel());
    }

}
