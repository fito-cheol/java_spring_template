package com.rest.api.service;

import com.rest.api.dto.MemberDTO;
import com.rest.api.entity.Member;
import com.rest.api.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void add() {
        String name = "백주희";
        MemberDTO memberDTO = new MemberDTO(null, name, "pivo", "20", LocalDate.of(2017, 7, 17), "주희백", 0, "", 1, 2);
        Long addedId = memberService.add(memberDTO);
        Optional<Member> byId = memberRepository.findById(addedId);
        if (byId.isEmpty()){
            fail("추가된 Member를 찾지 못함");
        }
        assertEquals(byId.get().getName(), name);
    }

    @Test
    void delete() {
        MemberDTO memberDTO = new MemberDTO(null, "백주희", "pivo", "20", LocalDate.of(2017, 7, 17), "주희백", 0, "", 1, 2);
        Long addedId = memberService.add(memberDTO);
        memberService.delete(addedId);
        Optional<Member> byId = memberRepository.findById(addedId);
        assert(byId.isEmpty());
    }

    @Test
    void getAll() {
        int initialSize = memberService.getAll().size();
        MemberDTO memberDTO = new MemberDTO(null, "백주희", "pivo", "20", LocalDate.of(2017, 7, 17), "주희백", 0, "", 1, 2);
        memberService.add(memberDTO);

        int laterSize = memberService.getAll().size();
        assert(initialSize+1 == laterSize);
    }

    @Test
    void findOne() {
        String name = "백주희";
        MemberDTO memberDTO = new MemberDTO(null, name, "pivo", "20", LocalDate.of(2017, 7, 17), "주희백", 0, "", 1, 2);
        Long addedId = memberService.add(memberDTO);
        MemberDTO memberDTO1 = memberService.findOne(addedId);
        assertEquals(memberDTO.getName(), memberDTO1.getName());
        assertEquals(memberDTO.getPosition(), memberDTO1.getPosition());
        assertEquals(memberDTO.getUniformNumber(), memberDTO1.getUniformNumber());
    }
}