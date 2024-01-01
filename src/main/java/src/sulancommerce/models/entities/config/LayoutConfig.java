package src.sulancommerce.models.entities.config;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "layout_config")
@Data
public class LayoutConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "layout_config_id")
    private Long layoutConfiId;

    @Column(name = "sn_bg_color")
    private Boolean snBgColor;

    @Column(name = "bg_color")
    private String bgColor;

    @Column(name = "sn_bg_img")
    private Boolean snBgImg;

    @Column(name = "bg_img")
    private String bgImg;

    @Column(name = "bg_header_color")
    private String bgHeaderColor;

    @Column(name = "header_brand")
    private String headerBrand;

    @Column(name = "bg_footer_color")
    private String bgFooterColor;

    @Column(name = "footer_brand")
    private String footerBrand;

    @Column(name = "button_color")
    private String buttonColor;

}
