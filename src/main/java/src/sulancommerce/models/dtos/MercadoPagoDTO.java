package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoDTO {
    private String payerFirstName;
    private String payerLastName;
    private String email;
    private String identificationType;
    private String identificationNumber;
    private Double transactionAmount;
    private String description;
}
