package com.markov.service;

import com.markov.pojo.Category;
import com.markov.util.Page;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory(Page page);
    int count();
    int add(Category category);
    int remove(String id);
    Category getCategoryByid(String id);
    void edit(Category category)throws Exception;

}
