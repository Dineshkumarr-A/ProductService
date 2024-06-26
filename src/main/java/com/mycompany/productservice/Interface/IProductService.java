package com.mycompany.productservice.Interface;

import com.mycompany.productservice.dtos.ProductDto;
import com.mycompany.productservice.exceptions.InvalidProductIdException;
import com.mycompany.productservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id) throws InvalidProductIdException;
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
