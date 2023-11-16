package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserResponse {
    private final long id;
    private final String userId;
    private final String userPw;
    private final String userNm;
    private final Date regDt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.userNm = user.getUserNm();
        this.regDt = user.getRegDt();
    }
}
