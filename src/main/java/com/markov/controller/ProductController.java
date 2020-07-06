package com.markov.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markov.pojo.Category;
import com.markov.pojo.Product;
import com.markov.service.ICategoryService;
import com.markov.service.IProductService;
import com.markov.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RequestMapping("")
@Controller
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @RequestMapping("admin_product_list")
    public String list(Model model,int cid,Page page){
        Category category=categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product>productList=productService.list(cid);

        int total= (int) new PageInfo<>(productList).getTotal();
        page.setTotal(total);
        model.addAttribute("page",page);
        model.addAttribute("ps",productList);
        model.addAttribute("c",category);
        return "admin/listProduct";
    }
    @RequestMapping("admin_product_add")
    public String add(Model model,Product product,Page page){
        int cid=product.getCid();
        product.setCreateDate(new Date());
        productService.add(product);
        model.addAttribute("cid",cid);
        return "redirect:/admin_product_list";
    }


}
