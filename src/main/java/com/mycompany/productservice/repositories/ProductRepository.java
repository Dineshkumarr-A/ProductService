package com.mycompany.productservice.repositories;

import com.mycompany.productservice.models.Category;
import com.mycompany.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    List<Product> findAll();
    Optional<Product> findByTitleAndDescription(String title,String description);
    Optional<Product> findByTitleContaining(String word);
    Optional<Product> findTopThreeByTitle(String title);
    List<Product> findByCategory(Category category);
    void deleteById(Long id);
    void deleteByTitle(String title);
    Product save(Product product);
}
