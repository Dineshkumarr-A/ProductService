package com.mycompany.productservice.services;

import com.mycompany.productservice.Interface.IProductService;
import com.mycompany.productservice.dtos.FakeStoreProductDto;
import com.mycompany.productservice.dtos.ProductDto;
import com.mycompany.productservice.models.Category;
import com.mycompany.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService {

    private final RestTemplate restTemplate;
    private final String url = "https://fakestoreapi.com/products";

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

    private Product convertProductDtoToProduct(ProductDto productDto)
    {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());

        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(url + "/" + id, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null)
            return null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] FakeStoreProductDtos = restTemplate.getForObject(url, FakeStoreProductDto[].class);

        if(FakeStoreProductDtos == null)
              return null;

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: FakeStoreProductDtos) {
           products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
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

    //Replace the product for the given id
    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, ProductDto.class);

        HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor<>(ProductDto.class,
                restTemplate.getMessageConverters());

        ProductDto responseProductDto =  restTemplate.execute(url + "/" + id, HttpMethod.PUT,requestCallback, responseExtractor);
        if(responseProductDto == null)
            return null;

        return convertProductDtoToProduct(responseProductDto);
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
