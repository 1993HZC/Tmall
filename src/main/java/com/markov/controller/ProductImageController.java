package com.markov.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.markov.pojo.ProductImage;
import com.markov.service.IProductImageService;
import com.markov.service.IProductService;
import com.markov.service.impl.ProductImageServiceImpl;
import com.markov.util.ImageUtil;
import com.markov.util.UploadImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.markov.pojo.Product;



@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    IProductService productService;

    @Autowired
    IProductImageService productImageService;


    @RequestMapping("admin_productImage_list")
    public String list(int pid, Model model) {
        Product p =productService.get(pid);
        List<ProductImage> pisSingle = productImageService.list(pid, productImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid, productImageService.type_detail);
        model.addAttribute("p", p);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
        return "admin/listProductImage";
    }
    @RequestMapping("admin_productImage_add")
    public String add(int pid, Model model, ProductImage productImage, HttpSession httpSession,UploadImageFile uploadImageFile) throws IOException {
        String realPath="img/";
        productImageService.add(productImage);
        if(productImage.getType().equals(productImageService.type_single)){
            realPath=realPath+"productSingle";
        }else{
            realPath=realPath+"productDetail";
        }
        File imageFolder=new File(httpSession.getServletContext().getRealPath(realPath));
        File file = new File(imageFolder,productImage.getId()+".jpg");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        uploadImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);

        return list(pid, model);
    }
    @RequestMapping("admin_productImage_delete")
    public String delete(int id,Model model,HttpSession httpSession){
        ProductImage productImage=productImageService.get(id);
        String realPath="img/";
        if(productImage.getType().equals(productImageService.type_single)){
            realPath=realPath+"productSingle";
        }else{
            realPath=realPath+"productDetail";
        }
        productImageService.delete(id);
        File imageFolder= new File(httpSession.getServletContext().getRealPath(realPath));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        int pid= productImage.getPid();
        return list(pid,model);
    }


}
