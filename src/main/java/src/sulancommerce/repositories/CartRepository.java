package src.sulancommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    @Query("SELECT ca FROM Cart ca order by ca.orderDate DESC")
    Page<Cart> getCartsPaged(Pageable pageable);
}
