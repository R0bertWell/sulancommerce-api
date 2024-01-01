package src.sulancommerce.services.config;

import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.config.LayoutConfigDTO;
import src.sulancommerce.models.entities.config.LayoutConfig;
import src.sulancommerce.repositories.config.LayoutConfigRepository;

import java.util.Optional;

@Service("layoutConfigService")
public class LayoutConfigServiceImpl implements LayoutConfigService {

    private final LayoutConfigRepository layoutConfigRepository;

    public LayoutConfigServiceImpl(LayoutConfigRepository layoutConfigRepository) {
        this.layoutConfigRepository = layoutConfigRepository;
    }

    @Override
    public LayoutConfigDTO getLayoutConfig() throws Exception {
        Optional<LayoutConfig> layoutConfigOpt = this.layoutConfigRepository.findById(1L);

        if(layoutConfigOpt.isEmpty())
            throw new Exception("Não existe configuração de layout cadastrada");

        return new LayoutConfigDTO(layoutConfigOpt.get());

    }

    @Override
    public LayoutConfigDTO updateLayoutConfig(LayoutConfigDTO layoutConfigToUpdate) throws Exception {
        Optional<LayoutConfig> layoutConfigOpt = this.layoutConfigRepository.findById(1L);

        if(layoutConfigOpt.isEmpty())
            throw new Exception("Não existe configuração de layout cadastrada");

        LayoutConfig layoutConfig = layoutConfigOpt.get();
        layoutConfigToUpdate.toEntity(layoutConfig);

        layoutConfig = this.layoutConfigRepository.save(layoutConfig);
        return new LayoutConfigDTO(layoutConfig);
    }
}
