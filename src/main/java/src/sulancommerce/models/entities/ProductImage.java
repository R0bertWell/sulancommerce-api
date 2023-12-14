package src.sulancommerce.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long productImageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "main_image", nullable = false)
    private boolean mainImage;

    public ProductImage(Product product, String imagePath, boolean mainImage){
        this.imagePath = imagePath;
        this.product = product;
        this.mainImage = mainImage;
    }

    public void setImagePathWithProductId(Long productId) {
        this.imagePath = "static/images/product_" + productId + "_" + productImageId;
    }
}
