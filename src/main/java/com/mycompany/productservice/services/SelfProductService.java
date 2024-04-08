package com.mycompany.productservice.services;

import com.mycompany.productservice.Interface.IProductService;
import com.mycompany.productservice.dtos.ProductDto;
import com.mycompany.productservice.models.Category;
import com.mycompany.productservice.models.Product;
import com.mycompany.productservice.repositories.CategoryRepository;
import com.mycompany.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository
    )
    {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty())
            return null;

        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();

        if(category.getId() == null)
        {
            Category savedCategory =  categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
