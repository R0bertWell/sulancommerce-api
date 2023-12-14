package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.sulancommerce.models.dtos.ColorDTO;
import src.sulancommerce.models.dtos.ProductDTO;
import src.sulancommerce.models.dtos.ProductInfoDTO;
import src.sulancommerce.models.dtos.SizeDTO;
import src.sulancommerce.services.ProductInfoService;

import java.util.List;

@RestController
@RequestMapping("/product-infos/")
public class ProductInfosController {
    private final ProductInfoService productInfoService;

    public ProductInfosController(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @PostMapping(value = "color/save")
    public ResponseEntity<HttpStatus> saveColor(@RequestBody ColorDTO color) throws Exception {
        this.productInfoService.saveColor(color);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "size/save")
    public ResponseEntity<HttpStatus> saveSize(@RequestBody SizeDTO size) throws Exception {
        this.productInfoService.saveSize(size);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "product/{productId}/colors")
    public ResponseEntity<List<ColorDTO>> getProductColors(@PathVariable Long productId) throws Exception {
        List<ColorDTO> colorDTOS = this.productInfoService.getProductColors(productId);
        return new ResponseEntity<>(colorDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "product/{productId}/color/{colorId}/sizes")
    public ResponseEntity<List<SizeDTO>> getSizesOfProductByColors(@PathVariable Long productId,
                                                                    @PathVariable Long colorId) throws Exception {
        List<SizeDTO> sizeDTOS = this.productInfoService.getSizesOfProductByColor(productId, colorId);
        return new ResponseEntity<>(sizeDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "product/{productId}/color/{colorId}/size/{sizeId}/quantity")
    public ResponseEntity<Integer> getProductQuantityByColorSize(@PathVariable Long productId,
                                                                       @PathVariable Long colorId,
                                                                       @PathVariable Long sizeId) throws Exception {
        Integer quantity = this.productInfoService.getProductQuantityByColorSize(productId, colorId, sizeId);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

    @GetMapping(value = "colors")
    public ResponseEntity<List<ColorDTO>> getColors() throws Exception {
        List<ColorDTO> colorDTOS = this.productInfoService.getColors();
        return new ResponseEntity<>(colorDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "sizes")
    public ResponseEntity<List<SizeDTO>> getSizes() throws Exception {
        List<SizeDTO> sizeDTOS = this.productInfoService.getSizes();
        return new ResponseEntity<>(sizeDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "{productId}/product-infos")
    public ResponseEntity<Page<ProductInfoDTO>> getProductInfos(@PathVariable Long productId, Pageable pageable) throws Exception {
        Page<ProductInfoDTO> productInfoDTOS = this.productInfoService.getProductInfos(productId, pageable);
        return new ResponseEntity<>(productInfoDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "infos/save")
    public ResponseEntity<HttpStatus> saveProductInfos(@RequestBody List<ProductInfoDTO> productInfoDTOS){
        this.productInfoService.saveProductInfos(productInfoDTOS);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "info/product/{productId}/color/{colorId}/size/{sizeId}/update")
    public ResponseEntity<HttpStatus> updateProductInfoQuantity(@PathVariable Long productId,
                                                                @PathVariable Long colorId,
                                                                @PathVariable Long sizeId,
                                                                @RequestParam Integer quantity){
        this.productInfoService.updateProductInfoQuantity(productId, colorId, sizeId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "info/product/{productId}/color/{colorId}/size/{sizeId}/delete")
    public ResponseEntity<HttpStatus> deleteProductInfo(@PathVariable Long productId,
                                                        @PathVariable Long colorId,
                                                        @PathVariable Long sizeId){
        this.productInfoService.deleteProductInfo(productId, colorId, sizeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
