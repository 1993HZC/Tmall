package com.markov.controller;


import com.markov.pojo.Category;
import com.markov.pojo.TestCa;
import com.markov.service.ICategoryService;
import com.markov.util.ImageUtil;
import com.markov.util.Page;
import com.markov.util.UploadImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    public ICategoryService categoryService;
    @RequestMapping("admin_category_list")
    public String list(Model model, Page page){
        List<Category> categoryList=categoryService.getAllCategory(page);
        int total = categoryService.count();
        page.setTotal(total);
        model.addAttribute("cs",categoryList);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }
    @RequestMapping("admin_category_add")
    public String add(Model model, TestCa testCa, Category category, HttpSession httpSession, UploadImageFile uploadImageFile) throws IOException {
        System.out.println(testCa.getName());
        System.out.println(category.getId());
        int id=categoryService.add(category);
        File imageFolder=new File(httpSession.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,category.getId()+".jpg");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        uploadImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";

    }
}
