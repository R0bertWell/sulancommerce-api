package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.CategoryDTO;
import src.sulancommerce.repositories.CategoryRepository;
import src.sulancommerce.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

     private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("list")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByFilters(@RequestParam(defaultValue = "") String filter) {
        List<CategoryDTO> categoryDTOList = this.categoryService.getCategoriesByFilter(filter);
        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        this.categoryService.saveCategory(categoryDTO);

        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
}
