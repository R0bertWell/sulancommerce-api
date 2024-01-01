package src.sulancommerce.models.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Double transaction_amount;
    private String description;
    private String payment_method_id;
    private String date_of_expiration;
    private Payer payer;
}
