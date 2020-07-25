package com.markov.service;

import com.markov.pojo.User;

import java.util.List;

public interface IUserService {
    List<User>listUser();
    User get(int id);
    User getUserbyLogin(String name,String Password);
    boolean isExist(String name);
    void add(User user);
}
