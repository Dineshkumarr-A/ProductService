package com.mycompany.productservice.controllers;

import com.mycompany.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id)
    {
        return new Product();
    }

    @GetMapping
    public List<Product> getAllProducts()
    {
        return new ArrayList<>();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product)
    {
        return new Product();
    }
    //partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product)
    {
        return new Product();
    }

    //Replace Product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody Product product) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }
}
