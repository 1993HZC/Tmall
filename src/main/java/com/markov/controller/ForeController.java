package com.markov.controller;


import com.markov.pojo.Category;
import com.markov.pojo.User;
import com.markov.service.ICategoryService;
import com.markov.service.IProductService;
import com.markov.service.IPropertyService;
import com.markov.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
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
    @Autowired
    IUserService userService;
    @RequestMapping("forehome")
    public String home(Model model){
        List<Category> categoryList=categoryService.listall();
        productService.fillProduct(categoryList);
        productService.fillProductByRow(categoryList);
        model.addAttribute("cs", categoryList);
        return "fore/home";
    }
    @RequestMapping("foreregister")
    public String register(Model model, User user){
        String name =  user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if(exist){
            String m ="用户名已经被使用,不能使用";
            model.addAttribute("msg", m);
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.add(user);

        return "redirect:registerSuccessPage";
    }
    @RequestMapping("forelogin")
    public String login(Model model, User user, HttpSession httpSession){
        User result=userService.getUserbyLogin(user.getName(),user.getPassword());
        if(result==null){
            model.addAttribute("msg","账号密码错误");
            return "fore/login";
        }
        httpSession.setAttribute("user", user);
        return "redirect:forehome";
    }
    @RequestMapping("forelogout")
    public String logout(Model model,HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "fore/home";
    }
}
