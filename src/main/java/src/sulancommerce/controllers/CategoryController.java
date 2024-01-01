package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.CategoryDTO;
import src.sulancommerce.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class NewCategoryController {

    private final CategoryService categoryService;

    public NewCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("list")
    public ResponseEntity<Page<CategoryDTO>> getCategoriesByFilters(@RequestParam(defaultValue = "") String filter,
                                                                    Pageable pageable) {
        Page<CategoryDTO> categoryDTOs = this.categoryService.getCategoriesPaged(filter, pageable);
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    @PostMapping("category/save")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        this.categoryService.saveCategory(categoryDTO);

        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "category/{categoryId}/delete")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long categoryId) throws Exception {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
