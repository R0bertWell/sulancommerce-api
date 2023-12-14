package src.sulancommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.sulancommerce.models.entities.Color;
import src.sulancommerce.models.entities.ProductInfo;
import src.sulancommerce.models.entities.ProductInfoPK;
import src.sulancommerce.models.entities.Size;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, ProductInfoPK> {

    @Query("SELECT DISTINCT pi.productInfoId.color FROM ProductInfo pi WHERE pi.productInfoId.product.productId = :productId")
    List<Color> getColorsByProduct(@Param("productId") Long productId);

    @Query(" SELECT pi.productInfoId.size FROM ProductInfo pi " +
           " WHERE pi.productInfoId.product.productId = :productId " +
           " AND pi.productInfoId.color.colorId = :colorId")
    List<Size> getSizesByProductColor(@Param("productId") Long productId,
                                      @Param("colorId") Long colorId);

    @Query(" SELECT pi.quantity FROM ProductInfo pi " +
            " WHERE pi.productInfoId.product.productId = :productId " +
            " AND pi.productInfoId.color.colorId = :colorId " +
            " AND pi.productInfoId.size.sizeId = :sizeId ")
    Integer getQuantByProductColorSize(@Param("productId") Long productId,
                                          @Param("colorId") Long colorId,
                                          @Param("sizeId") Long sizeId);

    @Modifying
    @Transactional
    void deleteByProductInfoId_Product_ProductId(Long productId);

    @Query(" SELECT pi FROM ProductInfo pi " +
           " WHERE pi.productInfoId.product.productId = :productId ")
    Page<ProductInfo> getProductInfosByProductId(@Param("productId") Long productId, Pageable pageable);

    Optional<ProductInfo> findByProductInfoId(ProductInfoPK productInfoId);

    @Query("SELECT pi FROM ProductInfo pi WHERE pi.productInfoId.product.productId = :productId " +
           "AND pi.productInfoId.color.colorId = :colorId " +
           "AND pi.productInfoId.size.sizeId = :sizeId")
    Optional<ProductInfo> getProductInfoByProductColorSize(@Param("productId") Long productId,
                                                 @Param("colorId") Long colorId,
                                                 @Param("sizeId") Long sizeId);
}
