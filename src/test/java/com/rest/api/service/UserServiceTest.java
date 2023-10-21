package com.rest.api.service;

import com.rest.api.dto.UserDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
class UserServiceTest {
    final String EMAIL = "test@email.com";
    final String PASSWORD = "12345";
    final String USERNAME = "김정호";

    @Autowired
    UserService userService;

    @Test
    @DisplayName("비밀번호 암호화 테스트")
    void hashPassword() throws RuntimeException{
        assert(true);
        // given
        UserDTO user = new UserDTO(null, USERNAME, EMAIL, PASSWORD);;

        // when
        UserDTO newUser = userService.addUser(user);

        // then
        assertNotEquals(newUser.getPassword(), PASSWORD);
    }

}