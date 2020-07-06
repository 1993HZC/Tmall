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

        /*将这个&cid=这个参数存入到listProduct这个页面当中
        *
        * 从admin_category_list页面点击admin_product_list页面的时候admin_category_list会自带&cid=参数，所以page.setParam("&cid="+cid);可有可无是不会报错的
        * 从admin_category_list页面自己点击第2页或者下一页，jsp代码就从当前页上拿到点击当前页是已经存好的&cid=参数，如果之前第一次进入到这个页面没有存这个&cid=,那么在当前页面点击同属当前jsp的
        * 下一页就会报错
         * */
        page.setParam("&cid="+cid);
        model.addAttribute("page",page);
        model.addAttribute("ps",productList);
        model.addAttribute("c",category);
        model.addAttribute("cid",cid);
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
    @RequestMapping("admin_product_delete")
    public String delete(Model model,Product product,Page page,int id){
        product=productService.get(id);
        productService.delete(id);
        model.addAttribute("cid",product.getCid());
        return "redirect:/admin_product_list";
    }



}
