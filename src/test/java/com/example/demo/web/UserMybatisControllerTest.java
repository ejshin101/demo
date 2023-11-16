package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.repository.UserMybatisRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Mybatis CRUD TEST")
class UserMybatisControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserMybatisRepository userMybatisRepository;

    @BeforeEach
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        userMybatisRepository.deleteAll();
    }

    @DisplayName("Mybatis add user : 유저 추가에 성공한다.")
    @Test
    public void addUser() throws Exception{
        //given
        final String url = "/api2/user";
        final String userId = "userId";
        final String userNm = "userNm";
        final String userPw = "userPw";

        User savedUser = User.builder()
                .userId(userId)
                .userNm(userNm)
                .userPw(userPw)
                .regDt(new Timestamp(System.currentTimeMillis()))
                .build();

        //객체 JSON으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(savedUser);

        //When
        //설정한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //Then
        result.andExpect(status().isCreated());

        List<User> users = userMybatisRepository.selectAll();
        Assertions.assertThat(users.size()).isEqualTo(1);
        Assertions.assertThat(users.get(0).getUserId()).isEqualTo(userId);
        Assertions.assertThat(users.get(0).getUserPw()).isEqualTo(userPw);
        Assertions.assertThat(users.get(0).getUserNm()).isEqualTo(userNm);
    }

    @DisplayName("Mybatis find users : 유저 목록 조회에 성공한다")
    @Test
    public void findAllUsers() throws Exception {
        //given
        final String url = "/api2/user";
        final String userId = "userId";
        final String userNm = "userNm";
        final String userPw = "userPw";

        int savedUser = userMybatisRepository.insert(
                User.builder()
                        .userId(userId)
                        .userNm(userNm)
                        .userPw(userPw)
                        .regDt(new Timestamp(System.currentTimeMillis()))
                        .build()
        );

        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andExpect(jsonPath("$[0].userNm").value(userNm))
                .andExpect(jsonPath("$[0].userPw").value(userPw));
    }

    @DisplayName("Mybatis find user : 유저 조회에 성공한다")
    @Test
    public void findUser() throws Exception {
        //given
        final String url = "/api2/user/{userId}";
        final String userId = "userId";
        final String userNm = "userNm";
        final String userPw = "userPw";

        int savedUser = userMybatisRepository.insert(
                User.builder()
                        .userId(userId)
                        .userNm(userNm)
                        .userPw(userPw)
                        .regDt(new Timestamp(System.currentTimeMillis()))
                        .build()
        );

        //when
        final ResultActions resultActions = mockMvc.perform(get(url, userId));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.userNm").value(userNm))
                .andExpect(jsonPath("$.userPw").value(userPw));
    }

    @DisplayName("Mybatis update user : 유저 수정에 성공한다")
    @Test
    public void UpdateUser() throws Exception {
        //given
        final String url = "/api2/user/{userId}";
        final String userId = "userId";
        final String userNm = "userNm";
        final String userPw = "userPw";

        int savedUser = userMybatisRepository.insert(
                User.builder()
                        .userId(userId)
                        .userNm(userNm)
                        .userPw(userPw)
                        .regDt(new Timestamp(System.currentTimeMillis()))
                        .build()
        );

        final String newUserNm = "newUserNm";
        final String newUserPw = "newUserPw";

        User request = User.builder()
                .userNm(newUserNm)
                .userPw(newUserPw)
                .regDt(new Timestamp(System.currentTimeMillis()))
                .build();

        //when
        final ResultActions resultActions = mockMvc.perform(put(url, userId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions
                .andExpect(status().isOk());

        User user = userMybatisRepository.selectById(userId);

        Assertions.assertThat(user.getUserPw()).isEqualTo(newUserPw);
        Assertions.assertThat(user.getUserNm()).isEqualTo(newUserNm);
    }

    @DisplayName("Mybatis delete user : 유저 삭제에 성공한다")
    @Test
    public void deleteUser() throws Exception {
        //given
        final String url = "/api2/user/{userId}";
        final String userId = "userId";
        final String userNm = "userNm";
        final String userPw = "userPw";

        int savedUser = userMybatisRepository.insert(
                User.builder()
                        .userId(userId)
                        .userNm(userNm)
                        .userPw(userPw)
                        .regDt(new Timestamp(System.currentTimeMillis()))
                        .build()
        );

        //when
        mockMvc.perform(delete(url, userId))
                .andExpect(status().isOk());

        //then
        List<User> users = userMybatisRepository.selectAll();
        Assertions.assertThat(users).isEmpty();
    }
}