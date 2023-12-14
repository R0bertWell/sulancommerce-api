package src.sulancommerce.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductCategoryPK implements Serializable {
    @ManyToOne(cascade = { CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "category_id", nullable = false)
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryPK that = (ProductCategoryPK) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, category);
    }
}
