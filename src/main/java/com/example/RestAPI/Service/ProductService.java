package com.example.RestAPI.Service;


import com.example.RestAPI.dto.ProductDTO;
import com.example.RestAPI.Entity.Category;
import com.example.RestAPI.Entity.Product;
import com.example.RestAPI.Mapper.ProductMapper;
import com.example.RestAPI.Repository.CategoryRepository;
import com.example.RestAPI.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductDTO createProduct(ProductDTO productDTO) {


        Category category = new Category();
        category= categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new RuntimeException("category not found"));

        // DTO -> entity
        Product product = ProductMapper.toProductEntity(productDTO, category);
        product = productRepository.save(product);
        // Entity -> DTO
        return ProductMapper.toProductDTO(product);

    }
}
