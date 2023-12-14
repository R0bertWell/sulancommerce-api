package src.sulancommerce.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import src.sulancommerce.models.entities.Category;
import src.sulancommerce.models.entities.Product;
import src.sulancommerce.models.entities.ProductCategory;
import src.sulancommerce.models.entities.ProductImage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String productName;
    private String productDesc;
    private Double productValue;
    private boolean inStock;

    private Set<CategoryDTO> categories = new HashSet<>();
    private Set<ProductInfoDTO> productInfos = new HashSet<>();
    private List<ProductImageDTO> images = new ArrayList<>();

    public ProductDTO(Product product, List<ProductCategory> productCategories, List<ProductImage> productImages) {
        this.setDefaultProduct(product);
        if(!productCategories.isEmpty()){
            productCategories.forEach(prodCat -> {
                this.categories.add(new CategoryDTO(prodCat.getProductCategoryId().getCategory()));
            });
        }
        if(!productImages.isEmpty()){
            productImages.forEach(image -> {
                this.images.add(new ProductImageDTO(image.getProductImageId(), image.getImagePath(), image.isMainImage()));
            });
        }
    }

    public ProductDTO(Product product, List<ProductCategory> productCategories) {
        this.setDefaultProduct(product);
        if(!productCategories.isEmpty()){
            productCategories.forEach(prodCat -> {
                this.categories.add(new CategoryDTO(prodCat.getProductCategoryId().getCategory()));
            });
        }
    }

    public ProductDTO(Product product) {
        this.setDefaultProduct(product);
    }

    public Product toEntity(){
        Product product = new Product();
        product.setProductId(this.productId);
        product.setProductName(this.productName);
        product.setProductDesc(this.productDesc);
        product.setProductValue(this.productValue);
        product.setInStock(this.inStock);
        return product;
    }

    private void setDefaultProduct(Product product){
        this.setProductId(product.getProductId());
        this.setProductName(product.getProductName());
        this.setProductDesc(product.getProductDesc());
        this.setProductValue(product.getProductValue());
        this.setInStock(product.getInStock());
    }
}
