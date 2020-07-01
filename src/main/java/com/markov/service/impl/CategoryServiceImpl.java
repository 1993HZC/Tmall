package com.markov.service.impl;

import com.markov.dao.CategoryMapper;
import com.markov.pojo.Category;
import com.markov.service.ICategoryService;
import com.markov.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> getAllCategory(Page page) {
        return categoryMapper.list(page);
    }
    @Override
    public int count() {
        return categoryMapper.count();
    }

    @Override
    public int add(Category category) {
        return categoryMapper.add(category);
    }
}
