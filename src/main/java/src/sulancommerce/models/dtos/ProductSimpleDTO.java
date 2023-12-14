package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.Product;
import src.sulancommerce.models.entities.ProductImage;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSimpleDTO {
    private Long productId;
    private String productName;
    private String productDesc;
    private Double productValue;
    private boolean inStock;

    private List<ProductImageDTO> images = new ArrayList<>();

    public ProductSimpleDTO(Product product) {
        this.setDefaultSimpleProductDTO(product);

    }

    public ProductSimpleDTO(Product product, List<ProductImage> productImages) {
        this.setDefaultSimpleProductDTO(product);

        if(!productImages.isEmpty()){
            productImages.forEach(image -> {
                this.images.add(new ProductImageDTO(image.getProductImageId(), image.getImagePath(), image.isMainImage()));
            });
        }
    }

    private void setDefaultSimpleProductDTO(Product product){
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductDesc(product.getProductDesc());
        this.setProductValue(product.getProductValue());
        this.setInStock(product.getInStock());
    }

}