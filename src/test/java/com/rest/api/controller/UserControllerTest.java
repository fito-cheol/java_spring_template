package com.rest.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.util.CustomApiResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@AutoConfigureMockMvc // Ref: https://dev-gorany.tistory.com/354
@Transactional
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    final String email = "some@email.com";
    final String password = "p@ssw0rd2";

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @DisplayName("로그인 성공")
    void login_success() throws Exception{
        Map<String, String> input = new HashMap<>();
        input.put("email", "some@email.com");
        input.put("password", "p@ssw0rd2");
        String inputJson = objectMapper.writeValueAsString(input);

        mockMvc.perform(
                post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("유저 등록하기")
    void register_success() throws Exception{

        Map<String, String> input = new HashMap<>();
        input.put("email", email);
        input.put("password", password);
        String inputJson = objectMapper.writeValueAsString(input);

        MvcResult mvcResult = mockMvc.perform(
                        post("/api/user/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString  = mvcResult.getResponse().getContentAsString();
        CustomApiResponse customApiResponse = objectMapper.readValue(contentAsString, CustomApiResponse.class);
        Object data = customApiResponse.getData();

        String newUsername = "newUser";
        String newEmail = "newmail@email.co.kr";
        String newPassword = "P@ssw0rd";
        Map<String, String> new_input = new HashMap<>();
        new_input.put("username", newUsername);
        new_input.put("email", newEmail);
        new_input.put("password", newPassword);
        String registerBody = objectMapper.writeValueAsString(new_input);


        mockMvc.perform(post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerBody)
                        .header("Authorization", "Bearer " + data)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}