package com.markov.service.impl;

import com.markov.dao.PropertyValueMapper;
import com.markov.pojo.*;
import com.markov.service.IProductService;
import com.markov.service.IPropertyService;
import com.markov.service.IPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements IPropertyValueService {
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    IPropertyService propertyService;


    @Override
    public void init(Product p) {

        List<Property> propertyList = propertyService.list(p.getCid());

        for (Property property: propertyList) {
            PropertyValue pv = this.get(property.getId(),p.getId());
            if(null==pv){
                pv = new PropertyValue();
//                pid是产品编号
                pv.setPid(p.getId());
//                Ptid 所属产品的
                pv.setPtid(property.getId());
                propertyValueMapper.insert(pv);
            }
        }

    }
    @Override
    public PropertyValue get(int ptid, int pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPtidEqualTo(ptid).andPidEqualTo(pid);
        List<PropertyValue> pvs= propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty())
            return null;
        return pvs.get(0);
    }
    @Override
    public PropertyValue getPropertyValueByid(int id) {
        return propertyValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PropertyValue> getPropertyValueByPid(int pid) {
        PropertyValueExample example =new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");
        List<PropertyValue> propertyValueList=propertyValueMapper.selectByExample(example);
        for(PropertyValue propertyValue : propertyValueList){
            //获取属性名称
            propertyValue.setProperty(propertyService.get(propertyValue.getPtid()));
        }
        return propertyValueList;
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.updateByPrimaryKeySelective(pv);
    }
}
