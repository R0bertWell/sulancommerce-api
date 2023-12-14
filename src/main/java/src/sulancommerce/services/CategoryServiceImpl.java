package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.CategoryDTO;
import src.sulancommerce.models.dtos.SizeDTO;
import src.sulancommerce.models.entities.Category;
import src.sulancommerce.models.entities.Size;
import src.sulancommerce.repositories.CategoryRepository;
import src.sulancommerce.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) throws Exception {
        try {
            if (categoryDTO != null && !categoryDTO.getCategoryName().isEmpty()) {
                String categoryName = Utils.treatPercentString(categoryDTO.getCategoryName(), true, true);

                int categoryQuantity = this.categoryRepository.existCategoryByCategoryName(categoryName, categoryDTO.getCategoryId());

                if (categoryQuantity == 0) {
                    Category categoryToSave = categoryDTO.toEntity();
                    categoryToSave = this.categoryRepository.save(categoryToSave);
                    categoryDTO.setCategoryId(categoryToSave.getCategoryId());
                    return categoryDTO;
                } else {
                    throw new Exception("Categoria com descrição : " + categoryDTO.getCategoryName() + " já existente!");
                }
            } else {
                throw new Exception("Descrição da categoria não informada!");
            }
        } catch(Exception e) {
            throw new Exception("Não foi possível salvar a categoria, pois : " + e.getMessage());
        }
    }

    @Override
    public List<CategoryDTO> getCategoriesByFilter(String filter) {
        List<Category> categoryList = this.categoryRepository.getCategoriesByNameOrId(Utils.treatPercentString(filter, true, true));
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        categoryList.forEach(category -> categoryDTOList.add(new CategoryDTO(category)));

        return categoryDTOList;
    }

    @Override
    public Page<CategoryDTO> getCategoriesPaged(String filter, Pageable pageable) {
        Page<Category> sizePage = this.categoryRepository.getCategoriesByFilterPaged(Utils.treatPercentString(filter, true, true), pageable);
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        sizePage.getContent().forEach(category -> categoryDTOList.add(new CategoryDTO(category)));


        return new PageImpl<>(categoryDTOList, sizePage.getPageable(), sizePage.getTotalElements());
    }

    @Override
    public void deleteCategory(Long categoryId) throws Exception {
        Optional<Category> optionalCategory = this.categoryRepository.findById(categoryId);

        if(optionalCategory.isEmpty()){
            throw new Exception("Não foi possível remover esta categoria, provavelmente ela não existe mais, atualize a página ou faça uma nova pesquisa.");
        }

        try {
            this.categoryRepository.deleteById(categoryId);
        } catch(Exception e){
            throw new Exception("Não foi possível remover a categoria : " + optionalCategory.get().toString() + ", pois ela está em uso!");
        }

    }
}
