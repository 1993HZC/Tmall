package com.markov.controller;


import com.markov.pojo.Category;
import com.markov.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    public ICategoryService categoryService;
    @RequestMapping("admin_category_list")
    public String list(Model model){
        List<Category> categoryList=categoryService.getAllCategory();
        model.addAttribute("cs",categoryList);
        return "admin/listCategory";

    }
}
