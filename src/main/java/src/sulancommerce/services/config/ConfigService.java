package src.sulancommerce.services.config;

import src.sulancommerce.models.dtos.config.ConfigDTO;

public interface ConfigService {
    ConfigDTO getConfig();

    ConfigDTO updateConfig(ConfigDTO config) throws Exception;
}
