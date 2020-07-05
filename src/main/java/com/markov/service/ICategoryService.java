package com.markov.service;

import com.markov.pojo.Category;
import com.markov.util.Page;

import java.util.List;

public interface ICategoryService {

    List<Category> listall();

    void add(Category category);


    void delete(int id);

    Category get(int id);

    void update(Category category);
}
