package src.sulancommerce.models.dtos.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String id;
    private String description;
    private Double transaction_amount;
    private String date_of_expiration;

    @JsonProperty("point_of_interaction")
    private PointOfInteraction pointOfInteraction;
}
