package com.markov.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markov.pojo.Category;
import com.markov.service.ICategoryService;
import com.markov.util.ImageUtil;
import com.markov.util.Page;
import com.markov.util.UploadImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
    /*
    老办法 分页
     */
//    @RequestMapping("admin_category_list")
//    public String list(Model model, Page page){
//        List<Category> categoryList=categoryService.getAllCategory(page);
//        int total = categoryService.count();
//        page.setTotal(total);
//        model.addAttribute("cs",categoryList);
//        model.addAttribute("page", page);
//        return "admin/listCategory";
//    }


    @RequestMapping("admin_category_list")
    public String list(Model model, Page page){
        /*这句话就是启动分页的意思*/
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs= categoryService.listall();
        int total= (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs",cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
//    TestCa Category 会自动的初始化，遇到同名字段会被自动注入
    public String add(Model model,Category category, HttpSession httpSession, UploadImageFile uploadImageFile) throws IOException {
        System.out.println(category.getId());
        categoryService.add(category);
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
    @RequestMapping("admin_category_delete")
    public String remove(Model model,int id,HttpSession session){
//        删除数据库
        categoryService.delete(id);
//        删除图片
        File imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }
    @RequestMapping("admin_category_edit")
    public String edit(Model model,Category bbCategory,HttpSession session){
        //名字随便取但是得把这个bbCategory的信息传到下一个地方去
        model.addAttribute("c",bbCategory);
        return "admin/editCategory";
    }
    @RequestMapping("admin_category_update")
    public String update(Model model,Category newCategory,HttpSession httpSession,UploadImageFile uploadImageFile) throws Exception {
        categoryService.update(newCategory);
        MultipartFile image = uploadImageFile.getImage();
        if(null!=image &&!image.isEmpty()){
            File  imageFolder= new File(httpSession.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder,newCategory.getId()+".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list";
    }

}
