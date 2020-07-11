package com.markov.controller;


import com.markov.pojo.Category;
import com.markov.service.ICategoryService;
import com.markov.service.IProductService;
import com.markov.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IPropertyService propertyService;
    @Autowired
    IProductService productService;
    @RequestMapping("forehome")
    public String home(Model model){
        List<Category> categoryList=categoryService.listall();
        productService.fillProduct(categoryList);
        productService.fillProductByRow(categoryList);
        model.addAttribute("cs", categoryList);
        return "fore/home";
    }
}
