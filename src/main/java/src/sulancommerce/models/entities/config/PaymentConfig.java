package src.sulancommerce.models.entities.config;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "payment_config")
@Data
public class PaymentConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_config_id")
    private Long paymentConfigId;

    @Column(name = "api_bearer_token", nullable = false)
    private String apiBearerToken;

    @Column(name = "x_idempotency_key", nullable = false)
    private String xIdemKey;

    @Column(name = "payment_expiration_time")
    private Integer paymentExpTime;
}
