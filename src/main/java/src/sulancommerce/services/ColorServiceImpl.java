package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.ColorDTO;
import src.sulancommerce.models.entities.Color;
import src.sulancommerce.repositories.ColorRepository;
import src.sulancommerce.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("colorService")
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public Page<ColorDTO> getColorsPaged(String filter, Pageable pageable) {
        Page<Color> colorPage = this.colorRepository.getColorsByFilterPaged(Utils.treatPercentString(filter, true, true), pageable);
        List<ColorDTO> colorDTOList = new ArrayList<>();

        colorPage.getContent().forEach(color -> colorDTOList.add(new ColorDTO(color)));


        return new PageImpl<>(colorDTOList, colorPage.getPageable(), colorPage.getTotalElements());
    }

    @Override
    public void saveColor(ColorDTO color) {
        if(this.validateColor(color)){
            this.colorRepository.save(color.toEntity());
        }
    }

    @Override
    public void deleteColor(Long colorId) throws Exception {
        Optional<Color> optionalColor = this.colorRepository.findById(colorId);

        if(optionalColor.isEmpty()){
            throw new Exception("Não foi possível remover está cor, provavelmente ela não existe mais, atualize a página ou faça uma nova pesquisa.");
        }

        try {
            this.colorRepository.deleteById(colorId);
        } catch(Exception e){
            throw new Exception("Não foi possível a cor : " + optionalColor.get().toString() + ", pois ela está em uso!");
        }
    }

    private boolean validateColor(ColorDTO colorDTO){
        return !colorDTO.getColorName().trim().isEmpty() && !colorDTO.getColorHex().trim().isEmpty();
    }
}
