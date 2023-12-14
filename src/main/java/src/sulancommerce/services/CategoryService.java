package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) throws Exception;

    List<CategoryDTO> getCategoriesByFilter(String filter);

    Page<CategoryDTO> getCategoriesPaged(String filter, Pageable pageable);

    void deleteCategory(Long categoryId) throws Exception;
}
