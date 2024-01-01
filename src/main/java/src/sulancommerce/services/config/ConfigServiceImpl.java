package src.sulancommerce.services.config;

import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.config.ConfigDTO;
import src.sulancommerce.models.entities.config.Config;
import src.sulancommerce.repositories.config.ConfigRepository;

import java.util.Optional;

@Service("configService")
public class ConfigServiceImpl implements ConfigService{

    private final ConfigRepository configRepository;

    public ConfigServiceImpl(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public ConfigDTO getConfig() {
        Optional<Config> configOptional = this.configRepository.findById(1L);

        return configOptional.map(ConfigDTO::new).orElse(null);

    }

    @Override
    public ConfigDTO updateConfig(ConfigDTO configDTO) throws Exception {
        Optional<Config> configOptional = this.configRepository.findById(1L);

        if(configOptional.isEmpty()){
            throw new Exception("Não existe configuração!");
        }

        Config config = configOptional.get();

        configDTO.toEntity(config);

        config = this.configRepository.save(config);

        return new ConfigDTO(config);
    }
}
