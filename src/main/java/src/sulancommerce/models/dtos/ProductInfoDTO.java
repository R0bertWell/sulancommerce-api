package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDTO {
    private ProductDTO product;
    private ColorDTO color;
    private SizeDTO size;
    private Integer quantity;

    public ProductInfoDTO(ProductInfo productInfo){
        ProductDTO productDTO = new ProductDTO(productInfo.getProductInfoId().getProduct());
        ColorDTO colorDTO = new ColorDTO(productInfo.getProductInfoId().getColor());
        SizeDTO sizeDTO = new SizeDTO(productInfo.getProductInfoId().getSize());
        this.product = productDTO;
        this.color = colorDTO;
        this.size = sizeDTO;
        this.quantity = productInfo.getQuantity();
    }
    public ProductInfo toEntity() {
        ProductInfo productInfo = new ProductInfo();
        ProductInfoPK pk = new ProductInfoPK();

        Product product = new Product();
        product.setProductId(this.product.getProductId());
        pk.setProduct(product);
        Color color = new Color();
        color.setColorId(this.color.getColorId());
        pk.setColor(color);
        Size size = new Size();
        size.setSizeId(this.size.getSizeId());
        pk.setSize(size);
        productInfo.setProductInfoId(pk);
        productInfo.setQuantity(quantity);
        return productInfo;
    }
}
