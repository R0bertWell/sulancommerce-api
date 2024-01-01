package src.sulancommerce.models.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payer {
    private String email;
    private String first_name;
    private String last_name;
    private Identification identification;
    private Address address;
}
