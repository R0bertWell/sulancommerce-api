package src.sulancommerce.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import src.sulancommerce.models.dtos.ColorDTO;

public interface ColorService {
    Page<ColorDTO> getColorsPaged(String filter, Pageable pageable);

    void saveColor(ColorDTO color);

    void deleteColor(Long colorId) throws Exception;
}
