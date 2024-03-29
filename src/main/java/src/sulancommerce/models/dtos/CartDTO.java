package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.Cart;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cartId;
    private String excursion;
    private Date orderDate;
    private BigDecimal totalValue;
    private Boolean payed;
    private Date payedDate;
    private Boolean sent;
    private Date sentDate;

    private List<ProductInfoDTO> items;

    public CartDTO(Cart cart) {
        this.cartId = cart.getCartId();
        this.excursion = cart.getExcursion();
        this.orderDate = cart.getOrderDate();
        this.totalValue = cart.getTotalValue();
        this.payed = cart.isPayed();
        this.sent = cart.isSent();
        this.sentDate = cart.getSentDate();
        this.payedDate = cart.getPayedDate();
    }

    public Cart toEntity(){
        Cart cart = new Cart();
        cart.setCartId(cartId);
        cart.setExcursion(excursion);
        cart.setOrderDate(orderDate);
        cart.setTotalValue(totalValue);
        cart.setPayed(payed);
        cart.setPayedDate(payedDate);
        cart.setSent(sent);
        cart.setSentDate(sentDate);
        return cart;
    }
}
