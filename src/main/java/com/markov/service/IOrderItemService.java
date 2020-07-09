package com.markov.service;

import com.markov.pojo.Order;
import com.markov.pojo.OrderItem;

import java.util.List;

public interface IOrderItemService {
    void add(OrderItem c);

    void delete(int id);

    void update(OrderItem c);

    OrderItem get(int id);

    List<OrderItem> list();

    void fill(List<Order> os);

    void fill(Order o);

    void setProduct(List<OrderItem> ois);
}
