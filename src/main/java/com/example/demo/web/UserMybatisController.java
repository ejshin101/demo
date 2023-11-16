package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserMybatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api2")
public class UserMybatisController {
    private final UserMybatisService userMybatisService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserDto request) {
        userMybatisService.insert(request);
    }

    @GetMapping("/user/{userId}")
    public User findUserById(@PathVariable String userId) {
        System.out.println(userId);
        return userMybatisService.selectById(userId);
    }

    @GetMapping("/user")
    public List<User> findAllUser() {
        return userMybatisService.selectAll();
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable String userId) {
        System.out.println(userId);
        userMybatisService.deleteById(userId);
    }

    @PutMapping("/user/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody User request) {
        System.out.println(request.getUserId());
        System.out.println(request.getUserPw());
        System.out.println(request.getUserNm());
        userMybatisService.update(userId, request);
    }
}
