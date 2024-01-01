package src.sulancommerce.repositories.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.config.PaymentConfig;

@Repository

public interface PaymentConfigRepository extends JpaRepository<PaymentConfig, Long> {
}
