package src.sulancommerce.repositories.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.sulancommerce.models.entities.config.LayoutConfig;

@Repository
public interface LayoutConfigRepository extends JpaRepository<LayoutConfig, Long> {
}
