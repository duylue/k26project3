package com.example.K2426Project3.service.impl;

import com.example.K2426Project3.model.Product;
import com.example.K2426Project3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.K2426Project3.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> list() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        Optional<Product> p = productRepository.findById(id);
        if (p.isEmpty()) {
            throw new RuntimeException("khong ton tai");
        } else {
            Product product = p.get();
            return product;
        }

    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
