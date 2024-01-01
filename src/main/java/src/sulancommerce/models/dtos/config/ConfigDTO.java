package src.sulancommerce.models.dtos.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.config.Config;

import java.lang.reflect.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigDTO {
    private Long configId;
    private Boolean snWhatsapp;
    private String whatsappNumber;
    private Boolean snMercadopago;
    private String logoImgUrl;
    private String footerImgUrl;
    private Boolean snBgColor;
    private Boolean snBgImg;
    private String bgColor;
    private String bgImgPath;
    private String bgHeaderColor;
    private String bgFooterColor;


    public ConfigDTO(Config config) {
        this.configId = config.getConfigId();
        this.snWhatsapp = config.getSnWhatsapp();
        this.whatsappNumber = config.getWhatsappNumber();
        this.snMercadopago = config.getSnMercadopago();
        this.logoImgUrl = config.getLogoImgUrl();
        this.footerImgUrl = config.getFooterImgUrl();
        this.snBgColor = config.getSnBgColor();
        this.snBgImg = config.getSnBgImg();
        this.bgColor = config.getBgColor();
        this.bgImgPath = config.getBgImgPath();
        this.bgHeaderColor = config.getBgHeaderColor();
        this.bgFooterColor = config.getBgFooterColor();
    }

    public void toEntity(Config config) {
        Field[] fields = ConfigDTO.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                Object value = field.get(this);

                if (value != null) {
                    Field configField = Config.class.getDeclaredField(field.getName());
                    configField.setAccessible(true);
                    configField.set(config, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
