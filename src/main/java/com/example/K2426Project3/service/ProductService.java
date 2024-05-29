package com.example.K2426Project3.service;

import com.example.K2426Project3.model.Customer;
import com.example.K2426Project3.model.Product;
import com.example.K2426Project3.model.ProductFile;
import com.example.K2426Project3.model.Zone;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> list();
    void save(Product product);
    Product findById(int id);
    void delete(int id);
    ProductFile getFile(int fid);
    ProductFile saveFile(MultipartFile file);
    List<Product> listByIds(List<Integer> idList);
}

