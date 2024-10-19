package org.example.learn.spring.boot.web.mock.service;


import org.example.learn.spring.boot.web.mock.dao.UserRepository;
import org.example.learn.spring.boot.web.mock.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if (user.getUpdateTime() == null) {
            user.setUpdateTime(new Date());
        }

        return userRepository.save(user);
    }
}