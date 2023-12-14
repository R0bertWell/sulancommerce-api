package src.sulancommerce.services;

import org.springframework.web.multipart.MultipartFile;
import src.sulancommerce.models.entities.Product;

import java.util.List;

public interface ProductImageService {
    void uploadImage(MultipartFile[] file, Long productId, List<Boolean> mainImage) throws Exception;

    String getProductImageByProduct(Long productId);

    void deleteImages(List<Long> imagesToDelete);
}
