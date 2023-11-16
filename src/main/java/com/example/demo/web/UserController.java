package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody UserDto request) {
        User savedUser = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable String userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok()
                .body(new User(user.getId(), user.getUserId(), user.getUserPw(), user.getUserNm(), user.getRegDt()));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponse>> findAllUser() {
        List<UserResponse> users = userService.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(users);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody UserDto request) {
        userService.update(userId,request);
        return ResponseEntity.ok()
                .build();
    }
}
