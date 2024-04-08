package com.mycompany.productservice.controllers;

import com.mycompany.productservice.Interface.IProductService;
import com.mycompany.productservice.dtos.ProductDto;
import com.mycompany.productservice.models.Product;
import com.mycompany.productservice.services.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    ProductController(IProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id)
    {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product)
    {
        return productService.createProduct(product);
    }
    //partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product)
    {

        return productService.updateProduct(id, product);
    }

    //Replace Product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id,@RequestBody ProductDto productDto) {
        return productService.replaceProduct(id, productDto);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }
}
