package src.sulancommerce.models.entities.config;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "config")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "config_id")
    private Long configId;

    @Column(name = "sn_whatsapp")
    private Boolean snWhatsapp;

    @Column(name = "whatsapp_number", length = 20)
    private String whatsappNumber;

    @Column(name = "sn_mercadopago")
    private Boolean snMercadopago;

    @Column(name = "logo_img_url")
    private String logoImgUrl;

    @Column(name = "footer_img_url")
    private String footerImgUrl;

    @Column(name = "sn_bg_color")
    private Boolean snBgColor;

    @Column(name = "sn_bg_img")
    private Boolean snBgImg;

    @Column(name = "bg_color")
    private String bgColor;

    @Column(name = "bg_img_path")
    private String bgImgPath;

    @Column(name = "bg_header_color")
    private String bgHeaderColor;

    @Column(name = "bg_footer_color")
    private String bgFooterColor;
}
