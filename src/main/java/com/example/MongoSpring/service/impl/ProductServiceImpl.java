package com.example.MongoSpring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MongoSpring.Enity.Product;
import com.example.MongoSpring.Repository.ProductRepo;
import com.example.MongoSpring.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getProduct(String category) {
        return productRepo.findByCategory(category);
    }

    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product addProoducts(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product deleProduct(Product product) {
        Product delProduct = productRepo.findByName(product.getName()).get();
        productRepo.deleteByName(delProduct.getName());
        return delProduct;
    }

    @Override
    public Product updateProduct(Product product) {
        Product productVar = productRepo.findByName(product.getName()).get();
        productVar.setName(product.getName());
        productVar.setPrice(product.getPrice());
        productVar.setQuantity(product.getQuantity());
        productVar.setDescription(product.getDescription());
        productVar.setCategory(product.getCategory());
        productRepo.save(productVar);
        return productVar;
    }

    @Override
    public Product updateQuantity(String name, int quantity) {

        Product product = productRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o nome: " + name));

        if (product.getQuantity() + quantity < 0) {
            throw new RuntimeException("Quantidade insuficiente no estoque. Disponível: " + product.getQuantity());
        }

        product.setQuantity(product.getQuantity() + quantity);

        return productRepo.save(product);
    }

}