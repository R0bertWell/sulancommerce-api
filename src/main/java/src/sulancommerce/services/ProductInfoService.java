package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import src.sulancommerce.models.dtos.ColorDTO;
import src.sulancommerce.models.dtos.ProductInfoDTO;
import src.sulancommerce.models.dtos.SizeDTO;

import java.util.List;

public interface ProductInfoService {
    List<ColorDTO> getColors();
    List<SizeDTO> getSizes();

    void saveColor(ColorDTO color) throws Exception;

    void saveSize(SizeDTO size) throws Exception;

    List<ColorDTO> getProductColors(Long productId);

    List<SizeDTO> getSizesOfProductByColor(Long productId, Long colorId);

    Integer getProductQuantityByColorSize(Long productId, Long colorId, Long sizeId);

    void saveProductInfos(List<ProductInfoDTO> productInfoDTOS);

    Page<ProductInfoDTO> getProductInfos(Long productId, Pageable pageable);

    void updateProductInfoQuantity(Long productId, Long colorId, Long sizeId, Integer quantity);

    void deleteProductInfo(Long productId, Long colorId, Long sizeId);
}
