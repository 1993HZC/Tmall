package com.markov.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markov.pojo.Category;
import com.markov.pojo.Property;
import com.markov.service.ICategoryService;
import com.markov.service.IPropertyService;
import com.markov.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("")
@Controller
public class PropertyController {
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IPropertyService propertyService;
    @RequestMapping("admin_property_list")
    public String list(Model model, Page page,int cid){
        Category c = categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> Properties = propertyService.list(cid);

        int total = (int) new PageInfo<>(Properties).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps", Properties);
        model.addAttribute("c", c);
        model.addAttribute("page", page);

        return "admin/listProperty";
    }
    @RequestMapping("admin_property_add")
    public String add(Model model,Page page,Property property,int cid){
        propertyService.add(property);
        model.addAttribute("cid",cid);
//        model.addAttribute("page", page);
        return "redirect:/admin_property_list";
    }
    @RequestMapping("admin_property_delete")
    public String delete(Model model,Page page,Property property){
        int cid=propertyService.get(property.getId()).getCid();

        propertyService.delete(property.getId());
        model.addAttribute("cid",cid);
        return "redirect:/admin_property_list";
    }
    @RequestMapping("admin_property_edit")
    public String edit(Model model,int id){
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCid());
        property.setCategory(category);
        model.addAttribute("p",property);
        // 注意 这个addAttribute是向前端传数据，不是朝下面这哥update方法传数据，反正是行不通的，不要理解错了。
        model.addAttribute("huangzc","19930921");
        return "admin/editProperty";
    }
    @RequestMapping("admin_property_update")
//    newproperty是admin_property_edit传过来的
    public String update(Model model,Property newproperty,int id){
        propertyService.update(newproperty);
        model.addAttribute("cid",newproperty.getCid());
        return "redirect:/admin_property_list";
    }



}
