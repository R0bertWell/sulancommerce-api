package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import src.sulancommerce.models.dtos.CartDTO;

public interface OrderService {
    CartDTO saveOrder(CartDTO cartToSave);

    Page<CartDTO> getPagedOrders(Pageable pageable);
}
