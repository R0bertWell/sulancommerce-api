package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import src.sulancommerce.models.dtos.ProductDTO;
import src.sulancommerce.models.dtos.ProductTagDTO;

import java.util.List;

public interface ProductService {
    Page<ProductDTO> getProductsByFilters(String search, Long cdCategory, boolean inStock, Pageable pageable) throws Exception;

    List<ProductTagDTO> getProductsByCategories(Pageable pageable);

    ProductDTO salvarProduto(ProductDTO product);

    void deleteProduct(Long productId);

    void updateProductStock(Long productId, boolean inStock);

}
