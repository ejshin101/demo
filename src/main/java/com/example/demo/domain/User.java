package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column
    private String userId;

    @Column
    private String userPw;

    @Column
    private String userNm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date regDt;

    @Builder
    public User(Long id, String userId, String userPw, String userNm, Date regDt) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.regDt = regDt;
    }

    public void update(String userPw, String userNm, Date regDt) {
        this.userPw = userPw;
        this.userNm = userNm;
        this.regDt = regDt;
    }
}
