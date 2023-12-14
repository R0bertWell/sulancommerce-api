package src.sulancommerce.models.dtos;

import lombok.*;
import src.sulancommerce.models.entities.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;
    private Integer nrOrder;

    public CategoryDTO(Category category) {
        this.setCategoryId(category.getCategoryId());
        this.setCategoryName(category.getCategoryName());
        this.setNrOrder(category.getNrOrder());
    }

    public Category toEntity(){
        Category category = new Category();
        category.setCategoryId(this.categoryId);
        category.setCategoryName(this.categoryName);
        category.setNrOrder(this.nrOrder);
        return category;
    }
}
