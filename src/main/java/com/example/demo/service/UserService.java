package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User save(UserDto request) {
        return userRepository.save(request.toEntity());
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(String id, UserDto request) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        user.update(request.getUserPw(), request.getUserNm(), new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        return user;
    }
    public void delete(String id) {
        userRepository.deleteById(id);
    }

}
