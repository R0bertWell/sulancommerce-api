package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.Color;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColorDTO {
    private Long colorId;
    private String colorName;
    private String colorHex;

    public ColorDTO(Color color) {
        this.setColorId(color.getColorId());
        this.setColorName(color.getColorName());
        this.setColorHex(color.getColorHex());
    }

    public Color toEntity(){
        Color color = new Color();
        color.setColorId(this.colorId);
        color.setColorName(this.colorName);
        color.setColorHex(this.colorHex);
        return color;
    }
}