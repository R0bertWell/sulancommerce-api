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
public class TransactionData {
    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("ticket_url")
    private String ticketUrl;

    @JsonProperty("qr_code_base64")
    private String qrCodeBase64;
}
