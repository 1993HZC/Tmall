package com.markov.controller;


import com.markov.pojo.Product;
import com.markov.pojo.PropertyValue;
import com.markov.service.IProductService;
import com.markov.service.IPropertyService;
import com.markov.service.IPropertyValueService;
import com.markov.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
///需要一个product 以及一个productvalue
public class PropertyValueController {
    @Autowired
    IProductService productService;
    @Autowired
    IPropertyValueService propertyValueService;
    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model, int pid){

        Product product=productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> propertyValueList=propertyValueService.getPropertyValueByPid(pid);

        model.addAttribute("pvs",propertyValueList);
        model.addAttribute("p",product);

        return "admin/editPropertyValue";

    }
    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue pv) {
        propertyValueService.update(pv);
        return "success";
    }
}
