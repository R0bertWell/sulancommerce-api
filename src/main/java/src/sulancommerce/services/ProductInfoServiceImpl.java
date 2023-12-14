package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.ColorDTO;
import src.sulancommerce.models.dtos.ProductInfoDTO;
import src.sulancommerce.models.dtos.SizeDTO;
import src.sulancommerce.models.entities.*;
import src.sulancommerce.repositories.ColorRepository;
import src.sulancommerce.repositories.ProductInfoRepository;
import src.sulancommerce.repositories.SizeRepository;

import java.util.*;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
    private final ColorRepository colorRepository;
    private final ProductInfoRepository productInfoRepository;
    private final SizeRepository sizeRepository;

    public ProductInfoServiceImpl(ColorRepository colorRepository,
                                  ProductInfoRepository productInfoRepository,
                                  SizeRepository sizeRepository) {
        this.colorRepository = colorRepository;
        this.productInfoRepository = productInfoRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<ColorDTO> getColors() {
        List<Color> colors = this.colorRepository.findAll();
        List<ColorDTO> colorDTOS = new ArrayList<>();
        colors.forEach(color -> colorDTOS.add(new ColorDTO(color)));

        return colorDTOS;
    }

    @Override
    public List<SizeDTO> getSizes() {
        List<Size> sizes = this.sizeRepository.findAll();
        List<SizeDTO> sizeDTOS = new ArrayList<>();
        sizes.forEach(size -> sizeDTOS.add(new SizeDTO(size)));

        return sizeDTOS;
    }

    @Override
    public void saveColor(ColorDTO color) throws Exception {
        Color colorToSave = color.toEntity();
        this.colorRepository.save(colorToSave);
    }

    @Override
    public void saveSize(SizeDTO size) throws Exception {
        Size sizeToSave = size.toEntity();
        this.sizeRepository.save(sizeToSave);
    }

    @Override
    public List<ColorDTO> getProductColors(Long productId) {
        List<Color> colors = this.productInfoRepository.getColorsByProduct(productId);
        List<ColorDTO> colorsDTO = new ArrayList<>();
        colors.forEach(color -> colorsDTO.add(new ColorDTO(color)));

        return colorsDTO;
    }

    @Override
    public List<SizeDTO> getSizesOfProductByColor(Long productId, Long colorId) {
        List<Size> sizes = this.productInfoRepository.getSizesByProductColor(productId, colorId);
        List<SizeDTO> sizeDTOS = new ArrayList<>();
        sizes.forEach(size -> sizeDTOS.add(new SizeDTO(size)));

        return sizeDTOS;
    }

    @Override
    public Integer getProductQuantityByColorSize(Long productId, Long colorId, Long sizeId) {
        return this.productInfoRepository.getQuantByProductColorSize(productId, colorId, sizeId);
    }

    @Override
    public void saveProductInfos(List<ProductInfoDTO> productInfoDTOS) {
        Set<ProductInfo> productInfosToSave = new HashSet<>();
        productInfoDTOS.forEach(prodInfo -> {
            if(prodInfo.getProduct()  != null
            && prodInfo.getColor()  != null
            && prodInfo.getSize()  != null
            && prodInfo.getQuantity() != null) {
                Optional<ProductInfo> prodInfoOpt = this.productInfoRepository.getProductInfoByProductColorSize(
                        prodInfo.getProduct().getProductId(),
                        prodInfo.getColor().getColorId(),
                        prodInfo.getSize().getSizeId());
                prodInfoOpt.ifPresent(prodInfoExist -> prodInfo.setQuantity(prodInfo.getQuantity() + prodInfoExist.getQuantity()));
                productInfosToSave.add(prodInfo.toEntity());
            }
        });
        this.productInfoRepository.saveAll(productInfosToSave);
    }

    @Override
    public Page<ProductInfoDTO> getProductInfos(Long productId, Pageable pageable) {
        Page<ProductInfo> productInfos = this.productInfoRepository.getProductInfosByProductId(productId, pageable);
        List<ProductInfoDTO> productInfoDTOS = new ArrayList<>();

        productInfos.getContent().forEach(prodInfo -> {
            productInfoDTOS.add(new ProductInfoDTO(prodInfo));
        });

        return new PageImpl<>(productInfoDTOS, productInfos.getPageable(), productInfos.getTotalElements());
    }

    @Override
    public void updateProductInfoQuantity(Long productId, Long colorId, Long sizeId, Integer quantity) {
        ProductInfoPK productInfoPK = new ProductInfoPK(productId, colorId, sizeId);
        Optional<ProductInfo> productInfoOptional = this.productInfoRepository.findByProductInfoId(productInfoPK);
        if(productInfoOptional.isPresent()){
            ProductInfo productInfo = productInfoOptional.get();
            productInfo.setQuantity(quantity);
            this.productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void deleteProductInfo(Long productId, Long colorId, Long sizeId) {
        ProductInfoPK productInfoPK = new ProductInfoPK(productId, colorId, sizeId);
        Optional<ProductInfo> productInfoOptional = this.productInfoRepository.findByProductInfoId(productInfoPK);
        if(productInfoOptional.isPresent()){
            this.productInfoRepository.deleteById(productInfoPK);
        }
    }
}
