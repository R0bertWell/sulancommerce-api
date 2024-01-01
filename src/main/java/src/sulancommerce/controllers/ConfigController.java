package src.sulancommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.config.ConfigDTO;
import src.sulancommerce.models.dtos.config.LayoutConfigDTO;
import src.sulancommerce.models.dtos.config.PaymentConfigDTO;
import src.sulancommerce.services.config.ConfigService;
import src.sulancommerce.services.config.LayoutConfigService;
import src.sulancommerce.services.config.PaymentConfigService;

@RestController
@RequestMapping("/api/v1/configs")
public class ConfigController {

    private final LayoutConfigService layoutConfigService;
    private final PaymentConfigService paymentConfigService;
    private final ConfigService configService;

    public ConfigController(LayoutConfigService layoutConfigService,
                            PaymentConfigService paymentConfigService,
                            ConfigService configService) {
        this.layoutConfigService = layoutConfigService;
        this.paymentConfigService = paymentConfigService;
        this.configService = configService;
    }


    @GetMapping("")
    public ResponseEntity<ConfigDTO> getConfig(){
        ConfigDTO config = this.configService.getConfig();
        return new ResponseEntity<>(config, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<ConfigDTO> editConfig(@RequestBody ConfigDTO config) throws Exception {
        ConfigDTO configDTO = this.configService.updateConfig(config);
        return new ResponseEntity<>(configDTO, HttpStatus.OK);
    }

    @GetMapping("layout-config")
    public ResponseEntity<LayoutConfigDTO> getLayoutConfig() throws Exception {
        LayoutConfigDTO layoutConfig = this.layoutConfigService.getLayoutConfig();
        return new ResponseEntity<>(layoutConfig, HttpStatus.OK);
    }

    @PutMapping("layout-config/update")
    public ResponseEntity<LayoutConfigDTO> updateLayoutConfig(@RequestBody LayoutConfigDTO layoutConfigToUpdate) throws Exception {
        LayoutConfigDTO layoutConfig = this.layoutConfigService.updateLayoutConfig(layoutConfigToUpdate);
        return new ResponseEntity<>(layoutConfig, HttpStatus.OK);
    }

    @GetMapping("payment-config")
    public ResponseEntity<PaymentConfigDTO> getPaymentConfig() throws Exception {
        PaymentConfigDTO paymentConfig = this.paymentConfigService.getPaymentConfig();
        return new ResponseEntity<>(paymentConfig, HttpStatus.OK);
    }

    @PutMapping("payment-config/update")
    public ResponseEntity<PaymentConfigDTO> updatePaymentConfig(@RequestBody PaymentConfigDTO paymentConfigToUpdate) throws Exception {
        PaymentConfigDTO paymentConfigUpdated = this.paymentConfigService.updatePaymentConfig(paymentConfigToUpdate);
        return new ResponseEntity<>(paymentConfigUpdated, HttpStatus.OK);
    }
}
