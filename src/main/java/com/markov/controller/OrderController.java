package com.markov.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markov.pojo.Order;
import com.markov.service.IOrderItemService;
import com.markov.service.IOrderService;
import com.markov.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    IOrderService orderService;
    @Autowired
    IOrderItemService orderItemService;

    @RequestMapping("admin_order_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());

        List<Order> orderList= orderService.list();

        int total = (int) new PageInfo<>(orderList).getTotal();
        page.setTotal(total);

        orderItemService.fill(orderList);

        model.addAttribute("os", orderList);
        model.addAttribute("page", page);

        return "admin/listOrder";
    }
}
