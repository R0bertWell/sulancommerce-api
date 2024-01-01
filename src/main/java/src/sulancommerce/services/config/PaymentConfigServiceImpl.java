package src.sulancommerce.services.config;

import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.config.PaymentConfigDTO;
import src.sulancommerce.models.entities.config.PaymentConfig;
import src.sulancommerce.repositories.config.PaymentConfigRepository;

import java.util.Optional;

@Service("paymentConfigService")
public class PaymentConfigServiceImpl implements PaymentConfigService{
    private final PaymentConfigRepository paymentConfigRepository;

    public PaymentConfigServiceImpl(PaymentConfigRepository paymentConfigRepository) {
        this.paymentConfigRepository = paymentConfigRepository;
    }

    @Override
    public PaymentConfigDTO getPaymentConfig() throws Exception {
        Optional<PaymentConfig> paymentConfigOpt = this.paymentConfigRepository.findById(1L);
        if(paymentConfigOpt.isEmpty())
            throw new Exception("Não foi encontrada nenhuma configuração de pagamento!");

        return new PaymentConfigDTO(paymentConfigOpt.get());
    }

    @Override
    public PaymentConfigDTO updatePaymentConfig(PaymentConfigDTO paymentConfigToUpdate) throws Exception {
        Optional<PaymentConfig> paymentConfigOpt = this.paymentConfigRepository.findById(1L);
        if(paymentConfigOpt.isEmpty())
            throw new Exception("Não foi encontrada nenhuma configuração de pagamento!");

        PaymentConfig paymentConfig = paymentConfigOpt.get();
        paymentConfigToUpdate.toEntity(paymentConfig);

        paymentConfig = this.paymentConfigRepository.save(paymentConfig);

        return new PaymentConfigDTO(paymentConfig);
    }
}
