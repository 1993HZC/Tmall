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
    @Override
    public int remove(String id){return categoryMapper.remove(id);}
    @Override
    public Category getCategoryByid(String id){
        List<Category>Categorise=categoryMapper.getCategoryById(id);
        if (Categorise.size()!=1){
            System.out.println("不存在 或者重复");
            return null;
        }
        return Categorise.get(0);
    }
    @Override
    public void edit(Category newcategory) throws Exception{
        Category category=this.getCategoryByid(newcategory.getId());
        if (category==null){
            throw new Exception("没有该id对应的数据");
        }
        categoryMapper.edit(newcategory);



    }
}
