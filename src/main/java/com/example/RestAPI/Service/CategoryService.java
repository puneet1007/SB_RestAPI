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
}
