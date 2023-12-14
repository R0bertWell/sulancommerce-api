package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.ColorDTO;
import src.sulancommerce.models.dtos.SizeDTO;
import src.sulancommerce.models.entities.Color;
import src.sulancommerce.models.entities.Size;
import src.sulancommerce.repositories.SizeRepository;
import src.sulancommerce.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("sizeService")
public class SizeServiceImpl implements SizeService{

    private final SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }

    @Override
    public Page<SizeDTO> getSizesPaged(String filter, Pageable pageable) {
        Page<Size> sizePage = this.sizeRepository.getSizesByFilterPaged(Utils.treatPercentString(filter, true, true), pageable);
        List<SizeDTO> sizeDTOList = new ArrayList<>();

        sizePage.getContent().forEach(size -> sizeDTOList.add(new SizeDTO(size)));


        return new PageImpl<>(sizeDTOList, sizePage.getPageable(), sizePage.getTotalElements());
    }

    @Override
    public void saveSize(SizeDTO size) {
        if(this.validateSize(size)){
            this.sizeRepository.save(size.toEntity());
        }
    }

    @Override
    public void deleteSize(Long sizeId) throws Exception {
        Optional<Size> optionalSize = this.sizeRepository.findById(sizeId);

        if(optionalSize.isEmpty()){
            throw new Exception("Não foi possível remover está cor, provavelmente ela não existe mais, atualize a página ou faça uma nova pesquisa.");
        }

        try {
            this.sizeRepository.deleteById(sizeId);
        } catch(Exception e){
            throw new Exception("Não foi possível a cor : " + optionalSize.get().toString() + ", pois ela está em uso!");
        }
    }

    private boolean validateSize(SizeDTO sizeDTO){
        return !sizeDTO.getSizeName().trim().isEmpty();
    }
}
