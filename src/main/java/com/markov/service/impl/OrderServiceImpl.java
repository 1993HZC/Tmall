package com.markov.service.impl;

import com.markov.dao.OrderMapper;
import com.markov.pojo.Order;
import com.markov.pojo.OrderExample;
import com.markov.pojo.User;
import com.markov.service.IOrderService;
import com.markov.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IUserService userService;

    @Override
    public void add(Order c) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Order c) {
//        这个updateByPrimaryKeySelective要比普通的updateByPrimaryKey要安全的多
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public List<Order> list(){
        OrderExample example =new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> orderList =orderMapper.selectByExample(example);
        setUser(orderList);
        return orderList;
    }
    /*将每一笔订单的用户信息设置进去*/
    public void setUser(List<Order> orderList){
        for(Order order : orderList){
            int uid =order.getUid();
            User user =userService.get(uid);
            order.setUser(user);

        }
    }
}
