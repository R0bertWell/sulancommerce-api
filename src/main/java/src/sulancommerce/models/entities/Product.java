package src.sulancommerce.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_desc", columnDefinition = "text")
    private String productDesc;

    @Column(name = "product_value", nullable = false, precision = 5, scale = 2)
    private Double productValue;

    @Column(name = "in_stock", nullable = false)
    private Boolean inStock;

//    @OneToMany(mappedBy = "productCategoryId.product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<ProductCategory> productCategories = new HashSet<>();
//
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ProductImage> images = new ArrayList<>();
}
