package com.example.K2426Project3.service;

import com.example.K2426Project3.model.Customer;
import com.example.K2426Project3.model.Product;
import com.example.K2426Project3.model.Zone;

import java.util.List;

public interface ProductService {
    List<Product> list();
    void save(Product product);
    Product findById(int id);
    void delete(int id);
}

