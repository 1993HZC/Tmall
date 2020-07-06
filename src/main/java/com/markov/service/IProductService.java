package com.markov.service;

import com.markov.pojo.Product;
import com.markov.pojo.Property;

import java.util.List;

public interface IProductService {
    void add(Product p);

    void delete(int id);

    void update(Product p);

    Product get(int id);

    List<Product>list(int cid);
}
