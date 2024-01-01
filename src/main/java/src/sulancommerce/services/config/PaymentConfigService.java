package src.sulancommerce.services.config;

import src.sulancommerce.models.dtos.config.PaymentConfigDTO;

public interface PaymentConfigService {
    PaymentConfigDTO getPaymentConfig() throws Exception;

    PaymentConfigDTO updatePaymentConfig(PaymentConfigDTO paymentConfigToUpdate) throws Exception;
}
