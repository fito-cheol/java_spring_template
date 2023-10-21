package com.rest.api.service;

import com.rest.api.dto.MemberDTO;
import com.rest.api.entity.Member;
import com.rest.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long add(MemberDTO memberDTO){
        Member member = Member.toMember(memberDTO);
        Member save = memberRepository.save(member);
        return save.getId();
        // TODO: 중복된 경우 처리 할게 딱히 없음!
    }
    public void delete(Long memberId){
        memberRepository.deleteById(memberId);
    }

    public List<MemberDTO> getAll() {
        List<MemberDTO> memberDTOS = new ArrayList<>();

        for (Member member :memberRepository.findAll()){
            MemberDTO memberDTO = MemberDTO.toMemberDTO(member);
            memberDTOS.add(memberDTO);
        }
        return memberDTOS;
    }

    public MemberDTO findOne(Long memberId){
        Optional<Member> byId = memberRepository.findById(memberId);
        return byId.map(MemberDTO::toMemberDTO).orElse(null);
    }
}

