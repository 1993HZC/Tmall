package com.markov.service.impl;

import com.markov.dao.ProductImageMapper;
import com.markov.dao.ProductMapper;
import com.markov.pojo.*;
import com.markov.service.ICategoryService;
import com.markov.service.IProductImageService;
import com.markov.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        /*下面这三句话代表查询功能必须一气呵成（就是这三句话必须连在一起），否则分页功能不起作用*/
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List<Product>productList=productMapper.selectByExample(example);

        Category category=categoryService.get(cid);

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

    @Override
    public void fillProduct(List<Category> categoryList) {
        for (Category category:categoryList){
            int cid=category.getId();
            List<Product>productList=this.list(cid);
            category.setProducts(productList);
        }
    }

    @Override
    public void fillProductByRow(List<Category> categoryList) {
        int productNumberEachRow = 8;

        for (Category category :categoryList){
            List<Product>productList=category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();

            for(int i=0;i<productList.size();i=i+productNumberEachRow){
                int size = i+productNumberEachRow;
                size= size>productList.size()?productList.size():size;
                List<Product> productsOfEachRow =productList.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

}
