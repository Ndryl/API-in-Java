package com.example.MongoSpring.service;

import java.util.List;

import com.example.MongoSpring.Enity.Product;

public interface ProductService {

    public List<Product> getProducts();

    List<Product> getProduct(String category);

    public Product addProoducts(Product product);

    public Product deleProduct(Product product);

    public Product updateProduct(Product product);

    Product updateQuantity(String name, int quantity);

}