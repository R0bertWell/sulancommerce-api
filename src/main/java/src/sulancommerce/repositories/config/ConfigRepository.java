package src.sulancommerce.repositories.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.config.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
}
