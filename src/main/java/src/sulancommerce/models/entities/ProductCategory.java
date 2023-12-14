package src.sulancommerce.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_category")
@Data
public class ProductCategory {

    @EmbeddedId
    private ProductCategoryPK productCategoryId;

}