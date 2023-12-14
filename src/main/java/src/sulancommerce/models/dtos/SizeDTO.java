package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.Color;
import src.sulancommerce.models.entities.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SizeDTO {
    private Long sizeId;
    private String sizeName;

    public SizeDTO(Size size) {
        this.setSizeId(size.getSizeId());
        this.setSizeName(size.getSizeName());
    }

    public Size toEntity(){
        Size size = new Size();
        size.setSizeId(this.sizeId);
        size.setSizeName(this.sizeName);
        return size;
    }
}