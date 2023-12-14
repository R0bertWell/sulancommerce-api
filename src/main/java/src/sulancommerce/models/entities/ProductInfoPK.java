package src.sulancommerce.models.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductInfoPK implements Serializable {

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "productId", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "colorId", referencedColumnName = "color_id", nullable = false)
    private Color color;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "sizeId", referencedColumnName = "size_id", nullable = false)
    private Size size;

    // construtores, getters e setters

    public ProductInfoPK(Long productId, Long colorId, Long sizeId) {
        product = new Product();
        product.setProductId(productId);
        color = new Color();
        color.setColorId(colorId);
        size = new Size();
        size.setSizeId(sizeId);
    }

    // Lembre-se de implementar equals() e hashCode() para a classe ser usada corretamente em coleções
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfoPK that = (ProductInfoPK) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(color, that.color) &&
                Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, color, size);
    }
}