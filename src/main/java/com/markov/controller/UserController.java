package com.markov.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markov.pojo.User;
import com.markov.service.IUserService;
import com.markov.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("")
@Controller
public class UserController {
    @Autowired
    IUserService userService;
    @RequestMapping("admin_user_list")
    public String listUser(Model model, Page page){
        /*这句话就是启动分页的意思*/
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> us= userService.listUser();
        int total = (int) new PageInfo<>(us).getTotal();
        page.setTotal(total);

        model.addAttribute("us", us);
        model.addAttribute("page", page);

        return "admin/listUser";
    }

}
