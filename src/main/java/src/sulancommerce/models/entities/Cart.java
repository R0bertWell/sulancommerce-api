package src.sulancommerce.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "excursion", nullable = false)
    private String excursion;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "total_value", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "payed", nullable = false)
    private boolean payed;

    @Column(name = "payed_date")
    private Date payedDate;

    @Column(name = "sent", nullable = false)
    private boolean sent;

    @Column(name = "sent_date")
    private Date sentDate;

}
