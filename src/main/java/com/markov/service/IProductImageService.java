package com.markov.service;

import com.markov.pojo.ProductImage;

import java.util.List;

public interface IProductImageService {
    String type_single = "type_single";
    String type_detail = "type_detail";

    void add(ProductImage pi);
    void delete(int id);
    void update(ProductImage pi);
    ProductImage get(int id);
    List list(int pid, String type);

}
