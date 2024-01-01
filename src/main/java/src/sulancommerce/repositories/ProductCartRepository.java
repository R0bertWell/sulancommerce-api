package src.sulancommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.ProductCart;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

    @Query("SELECT pc FROM ProductCart pc WHERE pc.cart.cartId = :cartId")
    List<ProductCart> getProductsCartByCartId(@Param("cartId") Long cartId);
}
