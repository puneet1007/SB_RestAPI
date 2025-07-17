package com.example.RestAPI.Controller;

import com.example.RestAPI.Service.ProductService;
import com.example.RestAPI.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createProduct= productService.createProduct(productDTO);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id , @RequestBody ProductDTO productDTO){
    return productService.updateProduct(id,productDTO);
    }
    @GetMapping
    public List<ProductDTO> getAllProduct(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }



}