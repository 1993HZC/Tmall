package com.markov.service;

import com.markov.pojo.User;

import java.util.List;

public interface IUserService {
    List<User>listUser();

    User get(int id);
}
