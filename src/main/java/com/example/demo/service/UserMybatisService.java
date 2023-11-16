package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserMybatisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMybatisService {
    private final UserMybatisRepository userMybatisRepository;
    public void insert(UserDto UserDto) {
        userMybatisRepository.insert(UserDto.toEntity());
    }

    public List<User> selectAll() {
        return userMybatisRepository.selectAll();
    }

    public User selectById(String userId) {
        return userMybatisRepository.selectById(userId);
    }

    public void update(String userId,User User) {
        User user = userMybatisRepository.selectById(userId);

        user.update(User.getUserPw(), User.getUserNm(), new Timestamp(System.currentTimeMillis()));
        userMybatisRepository.update(user);
    }

    public void deleteById(String userId) {
        userMybatisRepository.deleteById(userId);
    }
    public void deleteAll() {userMybatisRepository.deleteAll();}
}
