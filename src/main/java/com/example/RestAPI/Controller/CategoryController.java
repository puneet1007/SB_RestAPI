package com.example.RestAPI.Controller;

import com.example.RestAPI.dto.CategoryDTO;
import com.example.RestAPI.Service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    // get all categories

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);

    }
@GetMapping
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();

}

@GetMapping("/{id}")
public CategoryDTO getCategoryById(@PathVariable long id){
     return  categoryService.getCategoryById(id);
    }
@DeleteMapping("/{id}")
public String deleteCategory(@PathVariable Long id){
return categoryService.deleteCategory(id);
}


}