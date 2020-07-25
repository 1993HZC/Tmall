package com.markov.service.impl;


import com.markov.dao.UserMapper;
import com.markov.pojo.PropertyValue;
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
    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean isExist(String name) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andNameEqualTo(name);
        List<User> userList=userMapper.selectByExample(userExample);
        if (userList.size()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }
    @Override
    public User getUserbyLogin(String name,String password) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> userList=userMapper.selectByExample(userExample);

        if (userList.isEmpty()){
            return null;
        }
        return userList.get(0);

    }

}
