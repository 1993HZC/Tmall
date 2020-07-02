package com.markov.dao;

import com.markov.pojo.Category;
import com.markov.util.Page;

import java.util.List;

public interface CategoryMapper {
    public List<Category>list(Page page);
    public int count();
    public int add(Category category);
    public int remove(String id);
    public List<Category> getCategoryById(String id);
    public void edit(Category category);

}
