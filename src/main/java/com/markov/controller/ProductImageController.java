package com.markov.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.markov.pojo.ProductImage;
import com.markov.service.IProductImageService;
import com.markov.service.IProductService;
import com.markov.service.impl.ProductImageServiceImpl;
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
        List<ProductImage> pisSingle = productImageService.list(pid, ProductImageServiceImpl.type_single);
        List<ProductImage> pisDetail = productImageService.list(pid, ProductImageServiceImpl.type_detail);


        model.addAttribute("p", p);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);

        return null;
    }
}
