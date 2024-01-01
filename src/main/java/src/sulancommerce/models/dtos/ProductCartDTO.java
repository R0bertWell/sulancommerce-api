package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCartDTO {
    private Long productCartId;
    private Long cartId;
    private Long productId;
    private Long colorId;
    private Long sizeId;
    private Integer productQuantity;
    private BigDecimal totalValue;
}
