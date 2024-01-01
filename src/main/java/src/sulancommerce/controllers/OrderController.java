package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.CartDTO;
import src.sulancommerce.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("order/save")
    public ResponseEntity<CartDTO> saveOrder(@RequestBody CartDTO cart_TO_SAVE) {
        CartDTO cart_saved = orderService.saveOrder(cart_TO_SAVE);
        return new ResponseEntity<>(cart_saved, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<CartDTO>> getOrders(Pageable pageable) {
        Page<CartDTO> pagedCart = orderService.getPagedOrders(pageable);
        return new ResponseEntity<>(pagedCart, HttpStatus.OK);
    }
}
