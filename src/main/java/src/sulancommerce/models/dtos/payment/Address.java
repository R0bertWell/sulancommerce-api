package src.sulancommerce.models.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String zip_code;
    private String street_name;
    private String street_number;
    private String neighborhood;
    private String city;
    private String federal_unit;
}
