package com.mycompany.productservice.controllers;

import com.mycompany.productservice.Interface.IProductService;
import com.mycompany.productservice.commons.AuthenticationCommons;
import com.mycompany.productservice.dtos.ProductDto;
import com.mycompany.productservice.dtos.UserDto;
import com.mycompany.productservice.exceptions.InvalidProductIdException;
import com.mycompany.productservice.exceptions.ProductControllerSpecificException;
import com.mycompany.productservice.models.Product;
import com.mycompany.productservice.services.FakeStoreProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;
    private AuthenticationCommons authenticationCommons;

    ProductController(IProductService productService, AuthenticationCommons authenticationCommons)
    {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id)  throws InvalidProductIdException
    {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/all/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token)
    {
        //Validate the token using userservice

        UserDto userDto = authenticationCommons.validateToken(token);

        if(userDto == null)
        {
            //token is invalid
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(products,HttpStatus.OK);
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
        productService.deleteProduct(id);
    }

    @ExceptionHandler(ProductControllerSpecificException.class)
    public ResponseEntity<Void> handleProductControllerSpecificException() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
