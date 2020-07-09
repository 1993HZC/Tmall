package com.markov.service.impl;


import com.markov.dao.UserMapper;
import com.markov.pojo.User;
import com.markov.pojo.UserExample;
import com.markov.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUser() {
        UserExample userExample=new UserExample();
        userExample.setOrderByClause("id desc");
        return userMapper.selectByExample(userExample);
    }
}
