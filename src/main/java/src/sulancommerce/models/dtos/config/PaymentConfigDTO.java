package src.sulancommerce.models.dtos.config;

import lombok.*;
import src.sulancommerce.models.entities.config.PaymentConfig;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentConfigDTO {
    private Long paymentConfigId;
    private String apiBearerToken;
    private String xIdemKey;
    private Integer paymentExpTime;

    public PaymentConfigDTO(PaymentConfig paymentConfig) {
        this.paymentConfigId = paymentConfig.getPaymentConfigId();
        this.apiBearerToken = paymentConfig.getApiBearerToken();
        this.xIdemKey = paymentConfig.getXIdemKey();
        this.paymentExpTime = paymentConfig.getPaymentExpTime();
    }

    public void toEntity(PaymentConfig paymentConfig) {
        paymentConfig.setPaymentConfigId(this.paymentConfigId != null ? this.paymentConfigId : paymentConfig.getPaymentConfigId());
        paymentConfig.setApiBearerToken(this.apiBearerToken != null ? this.apiBearerToken : paymentConfig.getApiBearerToken());
        paymentConfig.setPaymentExpTime(this.paymentExpTime != null ? this.paymentExpTime : paymentConfig.getPaymentExpTime());
        paymentConfig.setXIdemKey(this.xIdemKey != null ? this.xIdemKey : paymentConfig.getXIdemKey());
    }
}
