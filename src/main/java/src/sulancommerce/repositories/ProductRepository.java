package src.sulancommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(" SELECT DISTINCT p FROM Product p " +
           " LEFT JOIN ProductCategory pc ON p.productId = pc.productCategoryId.product.productId " +
           " WHERE (:categoryId IS NULL OR pc.productCategoryId.category.categoryId = :categoryId) " +
           " AND (upper(p.productName) like :search OR CAST(p.productId AS string) like :search) " +
           " AND p.inStock = :inStock ")
    Page<Product> getProductsByFiltersPaged(@Param("search") String search,
                                            @Param("categoryId") Long categoryId,
                                            @Param("inStock") boolean inStock,
                                            Pageable pageable);

    @Query(" SELECT p FROM Product p " +
           " JOIN ProductCategory pc ON p.productId = pc.productCategoryId.product.productId " +
           " WHERE pc.productCategoryId.category.categoryId = :categoryId")
    List<Product> getProductsByCategory(@Param("categoryId") Long categoryId);
}
