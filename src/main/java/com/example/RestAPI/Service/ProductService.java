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
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();

    }

    public ProductDTO getProductById(Long id){
     Product product=   productRepository.findById(id)
             .orElseThrow(()->new RuntimeException("Product not found"));
     return ProductMapper.toProductDTO(product);
    }
    public ProductDTO updateProduct(Long id , ProductDTO productDTO){
        Product product=   productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));
       Category category= categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category not found"));
       product.setName(productDTO.getName());
       product.setDescription(product.getDescription());
       product.setPrice(product.getPrice());
       product.setCategory(category);
       productRepository.save(product);
       return ProductMapper.toProductDTO(product);
    }

    public String deleteProduct(Long id ){
        productRepository.deleteById(id);
        return "Product "+id+" deleted";
    }


}
