package src.sulancommerce.models.dtos.config;

import lombok.*;
import src.sulancommerce.models.entities.config.LayoutConfig;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LayoutConfigDTO {
    private Long layoutConfigId;
    private Boolean snBgColor;
    private String bgColor;
    private Boolean snBgImg;
    private String bgImg;
    private String bgHeaderColor;
    private String headerBrand;
    private String bgFooterColor;
    private String footerBrand;
    private String buttonColor;

    public LayoutConfigDTO(LayoutConfig layoutConfig) {
        this.layoutConfigId = layoutConfig.getLayoutConfiId();
        this.snBgColor = layoutConfig.getSnBgColor();
        this.bgColor = layoutConfig.getBgColor();
        this.snBgImg = layoutConfig.getSnBgImg();
        this.bgImg = layoutConfig.getBgImg();
        this.bgHeaderColor = layoutConfig.getBgHeaderColor();
        this.headerBrand = layoutConfig.getHeaderBrand();
        this.bgFooterColor = layoutConfig.getBgFooterColor();
        this.footerBrand = layoutConfig.getFooterBrand();
        this.buttonColor = layoutConfig.getButtonColor();
    }

    public void toEntity(LayoutConfig layoutConfig) {
        layoutConfig.setLayoutConfiId(this.layoutConfigId != null ? this.layoutConfigId : layoutConfig.getLayoutConfiId());
        layoutConfig.setSnBgColor(this.snBgColor != null ? this.snBgColor : layoutConfig.getSnBgColor());
        layoutConfig.setBgColor(this.bgColor != null ? this.bgColor : layoutConfig.getBgColor());
        layoutConfig.setSnBgImg(this.snBgImg != null ? this.snBgImg : layoutConfig.getSnBgImg());
        layoutConfig.setBgImg(this.bgImg != null ? this.bgImg : layoutConfig.getBgImg());
        layoutConfig.setBgHeaderColor(this.bgHeaderColor != null ? this.bgHeaderColor : layoutConfig.getBgHeaderColor());
        layoutConfig.setHeaderBrand(this.headerBrand != null ? this.headerBrand : layoutConfig.getHeaderBrand());
        layoutConfig.setBgFooterColor(this.bgFooterColor != null ? this.bgFooterColor : layoutConfig.getBgFooterColor());
        layoutConfig.setFooterBrand(this.footerBrand != null ? this.footerBrand : layoutConfig.getFooterBrand());
        layoutConfig.setButtonColor(this.buttonColor != null ? this.buttonColor : layoutConfig.getButtonColor());
    }
}
