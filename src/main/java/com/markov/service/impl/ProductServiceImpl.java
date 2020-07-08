package com.markov.service.impl;

import com.markov.dao.ProductImageMapper;
import com.markov.dao.ProductMapper;
import com.markov.pojo.*;
import com.markov.service.ICategoryService;
import com.markov.service.IProductImageService;
import com.markov.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    IProductImageService productImageService;
    @Autowired
    ICategoryService categoryService;


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
        productMapper.updateByPrimaryKeySelective(p);

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
        Category category=categoryService.get(cid);


        List<Product>productList=productMapper.selectByExample(example);

        for (Product product:productList){
            int pid=product.getId();
            product.setCategory(category);
            List<ProductImage> productImageList=productImageService.list(pid,productImageService.type_single);
            if (productImageList.size()<1){
                continue;
            }
            ProductImage firstProductImage=productImageList.get(0);
            product.setFirstProductImage(firstProductImage);
        }

        return productList;

    }
    
}
