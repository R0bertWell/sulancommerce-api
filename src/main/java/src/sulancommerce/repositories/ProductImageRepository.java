package src.sulancommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import src.sulancommerce.models.entities.ProductImage;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Query("SELECT pi.imagePath FROM ProductImage pi WHERE pi.product.productId = :productId")
    List<String> getImagePathByProductId(Long productId);

    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.productId = :productId")
    List<ProductImage> findProductImagesByProductId(Long productId);
}
