package com.mycompany.productservice.services;

import com.mycompany.productservice.Interface.IProductService;
import com.mycompany.productservice.dtos.FakeStoreProductDto;
import com.mycompany.productservice.models.Category;
import com.mycompany.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null)
            return null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        FakeStoreProductDto[] fakeStoreProductDtos = responseEntity.getBody();
        if(fakeStoreProductDtos == null)
            return null;

        List<FakeStoreProductDto> fakeStoreProductDtoList = Arrays.stream(fakeStoreProductDtos).toList();

        List<Product> products = new ArrayList<>();
        Product product = new Product();

        for (FakeStoreProductDto fakeStoreProductDto:
             fakeStoreProductDtoList) {
            product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
            products.add(product);
        }

        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
