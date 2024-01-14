package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import src.sulancommerce.models.dtos.CartDTO;
import src.sulancommerce.models.dtos.ProductCartDTO;

import java.util.List;

public interface OrderService {
    CartDTO saveOrder(CartDTO cartToSave);

    CartDTO updateOrder(CartDTO cartToUpdate, List<ProductCartDTO> productCartsToAdd, List<ProductCartDTO> productCartsToRemove);


    Page<CartDTO> getPagedOrders(Pageable pageable);

    void updatePayedOrder(Long orderId, boolean payed) throws Exception;

    void updateSentOrder(Long orderId, boolean sent) throws Exception;
}
