package src.sulancommerce.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_info")
public class ProductInfo {
    @EmbeddedId
    private ProductInfoPK productInfoId;

    @Column(name = "quantity")
    private Integer quantity;
}
