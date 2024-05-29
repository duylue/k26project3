package com.example.K2426Project3.controller;

import com.example.K2426Project3.model.Customer;
import com.example.K2426Project3.model.Product;
import com.example.K2426Project3.model.ProductFile;
import com.example.K2426Project3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/product")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String get(Model model) {
        List<Product> list = productService.list();
        model.addAttribute("list", list);
        return "product/list";
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("pro", new Product());
        return "product/save";
    }

    @PostMapping("/save")
    public String postSave(Model model, @ModelAttribute Product product, @RequestParam MultipartFile file) {
        ProductFile productFile = productService.saveFile(file);
        product.setFid(productFile.getFid());
        productService.save(product);
        return "redirect:/product";
    }
    @GetMapping("/getFile")
    public ResponseEntity<?> postSave( @RequestParam int fid) {
        ProductFile productFile = productService.getFile(fid);
        return ResponseEntity.status(200).contentType(MediaType.parseMediaType(productFile.getContentType())).body(productFile.getContent());
    }
}
