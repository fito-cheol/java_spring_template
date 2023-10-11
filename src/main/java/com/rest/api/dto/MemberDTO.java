package com.rest.api.dto;

import com.rest.api.entity.Member;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private Long id;
    private String name;
    private String position;
    private String uniformNumber;
    private LocalDate joinDate;
    private String nickName;
    private int inflowRoute;
    private String bgImage;
    private int grade;
    private int level;

    public static MemberDTO toMemberDTO(Member member){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberDTO.getId());
        memberDTO.setName(member.getName());
        memberDTO.setPosition(member.getPosition());
        memberDTO.setUniformNumber(member.getUniformNumber());
        memberDTO.setJoinDate(member.getJoinDate());
        memberDTO.setNickName(member.getNickName());
        memberDTO.setInflowRoute(member.getInflowRoute());
        memberDTO.setBgImage(member.getBgImage());
        memberDTO.setGrade(member.getGrade());
        memberDTO.setLevel(member.getLevel());
        return memberDTO;
    }
}
