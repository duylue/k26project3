package com.example.K2426Project3.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.K2426Project3.model.Product;
import com.example.K2426Project3.model.ProductFile;
import com.example.K2426Project3.repository.FileRepository;
import com.example.K2426Project3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.K2426Project3.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
@Autowired
private FileRepository fileRepository;
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

    @Override
    public ProductFile getFile(int fid) {
        ProductFile file = fileRepository.findById(fid).get();
        return file ;
    }

    @Override
    public List<Product> listByIds(List<Integer> idList) {
        return productRepository.findAllById(idList);
    }

    @Override
    public ProductFile saveFile(MultipartFile file) {
        try {
            ProductFile productFile = new ProductFile();
            productFile.setFileName(file.getOriginalFilename());
            productFile.setContent(file.getBytes());
            productFile.setContentType(file.getContentType());
            return fileRepository.save(productFile);
        }catch (Exception  e){
            throw new RuntimeException(e);
        }

    }
}
