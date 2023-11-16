package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String userPw;
    private String userNm;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userNm(userNm)
                .userPw(userPw)
                .regDt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
