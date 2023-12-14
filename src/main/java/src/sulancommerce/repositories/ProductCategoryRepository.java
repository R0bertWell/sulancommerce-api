package src.sulancommerce.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.ProductCategory;
import src.sulancommerce.models.entities.ProductCategoryPK;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategoryPK> {

    @Modifying
    @Transactional
    void deleteByProductCategoryId_Product_ProductId(Long productId);

    @Query("SELECT pc FROM ProductCategory pc WHERE pc.productCategoryId.product.productId = :productId")
    List<ProductCategory> findProductCategoriesByProductId(@Param("productId") Long productId);
}
