package src.sulancommerce.services.config;

import src.sulancommerce.models.dtos.config.LayoutConfigDTO;

public interface LayoutConfigService {
    LayoutConfigDTO getLayoutConfig() throws Exception;

    LayoutConfigDTO updateLayoutConfig(LayoutConfigDTO layoutConfigToUpdate) throws Exception;
}
