package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import src.sulancommerce.models.dtos.SizeDTO;

public interface SizeService {
    Page<SizeDTO> getSizesPaged(String filter, Pageable pageable);

    void saveSize(SizeDTO size);

    void deleteSize(Long sizeId) throws Exception;
}
