package com.kenet.springbootstarter.service.impl;

import com.kenet.springbootstarter.entity.User;
import com.kenet.springbootstarter.mapper.UserMapper;
import com.kenet.springbootstarter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void create(User user) {
        userMapper.create(user);
    }

    /*
    * 批量删除，指定一系列ID
    * */
    @Override
    public void delete(Long... ids) {
        for (Long id : ids) {
            userMapper.delete(id);
        }
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}
