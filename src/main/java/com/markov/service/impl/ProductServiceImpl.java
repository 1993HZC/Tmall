package com.markov.service.impl;

import com.markov.dao.ProductMapper;
import com.markov.pojo.Product;
import com.markov.pojo.ProductExample;
import com.markov.pojo.Property;
import com.markov.pojo.PropertyExample;
import com.markov.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(Product p) {
        productMapper.insert(p);

    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {

    }

    @Override
    public Product get(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> list(int cid) {

        ProductExample example=new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");

        return productMapper.selectByExample(example);

    }
}
