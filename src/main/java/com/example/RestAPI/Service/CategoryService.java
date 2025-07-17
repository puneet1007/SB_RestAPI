package com.example.RestAPI.Service;

import com.example.RestAPI.dto.CategoryDTO;
import com.example.RestAPI.Entity.Category;
import com.example.RestAPI.Mapper.CategoryMapper;
import com.example.RestAPI.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {


    private CategoryRepository categoryRepository;

    // create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){

        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);

    }

    public List<CategoryDTO> getAllCategories(){
        return categoryRepository.findAll().stream().map(CategoryMapper::toCategoryDTO).toList();

    }
    public CategoryDTO getCategoryById(Long id){
       Category category= categoryRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("category not found"));
    return CategoryMapper.toCategoryDTO(category);
    }
    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "Category "+id+" deleted";
    }
}
