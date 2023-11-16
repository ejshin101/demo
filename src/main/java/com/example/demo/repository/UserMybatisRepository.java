package com.example.demo.repository;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMybatisRepository {
    public int insert(User user);
    public List<User> selectAll();
    public User selectById(String userId);
    public int update(User User);
    public void deleteById(String userId);
    public void deleteAll();

}
