package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.CartDTO;
import src.sulancommerce.models.dtos.ProductCartDTO;
import src.sulancommerce.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<Page<CartDTO>> getOrders(Pageable pageable) {
        Page<CartDTO> pagedCart = orderService.getPagedOrders(pageable);
        return new ResponseEntity<>(pagedCart, HttpStatus.OK);
    }

    @PostMapping("order/save")
    public ResponseEntity<CartDTO> saveOrder(@RequestBody CartDTO cart_TO_SAVE) {
        CartDTO cart_saved = orderService.saveOrder(cart_TO_SAVE);
        return new ResponseEntity<>(cart_saved, HttpStatus.OK);
    }

    @PutMapping("order/{orderId}/update-payed")
    public ResponseEntity<HttpStatus> updatePayedOrder(@PathVariable Long orderId,
                                                       @RequestParam(required = true) boolean payed) throws Exception {
        orderService.updatePayedOrder(orderId, payed);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("order/{orderId}/update-sent")
    public ResponseEntity<HttpStatus> updateSentOrder(@PathVariable Long orderId,
                                                  @RequestParam(required = true) boolean sent) throws Exception {
        orderService.updateSentOrder(orderId, sent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("order/update")
    public ResponseEntity<CartDTO> updateOrder(@RequestBody CartDTO cart_TO_UPDATE,
                                               @RequestBody(required = false) List<ProductCartDTO> productCartsToAdd,
                                               @RequestBody(required = false) List<ProductCartDTO> productCartsToRemove) {
        CartDTO cart_updated = orderService.updateOrder(cart_TO_UPDATE, null, null);
        return new ResponseEntity<>(cart_updated, HttpStatus.OK);
    }
}
