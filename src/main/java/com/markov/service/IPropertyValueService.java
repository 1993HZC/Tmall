package com.markov.service;

import com.markov.pojo.Product;
import com.markov.pojo.PropertyValue;

import java.util.List;

public interface IPropertyValueService {
    void init(Product p);

    PropertyValue get(int ptid, int pid);

    PropertyValue getPropertyValueByid(int id);
    List<PropertyValue> getPropertyValueByPid(int pid);
    void update(PropertyValue pv);
}
