package com.markov.service;

import com.markov.pojo.Property;

import java.util.List;

public interface IPropertyService {
    void add(Property p);

    void delete(int id);

    void update(Property p);

    Property get(int id);

    List list(int cid);
}
