package com.markov.service.impl;

import com.markov.dao.OrderItemMapper;
import com.markov.pojo.Order;
import com.markov.pojo.OrderItem;
import com.markov.pojo.OrderItemExample;
import com.markov.pojo.Product;
import com.markov.service.IOrderItemService;
import com.markov.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemServiceImpl implements IOrderItemService {
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    IProductService productService;

    @Override
    public void add(OrderItem c) {
        orderItemMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem c) {
        orderItemMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem result = orderItemMapper.selectByPrimaryKey(id);
        setProduct(result);
        return result;
    }

    @Override
    public List<OrderItem> list(){
        OrderItemExample example =new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);

    }

    @Override
    public void fill(List<Order> orderList) {
        for(Order order : orderList){
            fill(order);
        }
    }




    @Override
    /*找到该笔订单信息中所购买的商品列表，并找到该商品的详细product信息*/
    public void fill(Order o) {
        OrderItemExample example =new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> orderItemList =orderItemMapper.selectByExample(example);
        setProduct(orderItemList);


    }

    @Override
    public void setProduct(List<OrderItem> orderItemList){
        for (OrderItem orderItem: orderItemList) {
            setProduct(orderItem);
        }
    }

    private void setProduct(OrderItem orderItem) {
        Product product = productService.get(orderItem.getPid());
        orderItem.setProduct(product);
    }

}