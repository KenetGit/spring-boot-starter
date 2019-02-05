package com.kenet.springbootstarter.service;

import com.kenet.springbootstarter.entity.User;

public interface UserService extends BaseService<User> {

    User findByName(String name);
}

