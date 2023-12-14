package src.sulancommerce.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import src.sulancommerce.models.dtos.ProductDTO;
import src.sulancommerce.models.dtos.ProductTagDTO;
import src.sulancommerce.services.ProductImageService;
import src.sulancommerce.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductImageService productImageService;
    private final ProductService productService;

    public ProductController(ProductImageService productImageService, ProductService productService) {
        this.productImageService = productImageService;
        this.productService = productService;
    }

    @GetMapping(value = "list")
    public ResponseEntity<Page<ProductDTO>> getProductListByFilters(@RequestParam(defaultValue = "", required = false) String search,
                                                                    @RequestParam(defaultValue = "true") boolean inStock,
                                                                    @RequestParam(defaultValue = "-1", required = false) Long categoryId,
                                                                    Pageable pageable) throws Exception {
        categoryId = categoryId != -1 ? categoryId : null;
                Page<ProductDTO> productDTOS = this.productService.getProductsByFilters(search, categoryId, inStock, pageable);
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "products-by-categories")
    public ResponseEntity<List<ProductTagDTO>> getProductsByCategories(Pageable pageable) throws Exception {
        List<ProductTagDTO> productTagDTOS = this.productService.getProductsByCategories(pageable);
        return new ResponseEntity<>(productTagDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "{productId}/image-path")
    public ResponseEntity<String> getProductListByFilters(@PathVariable Long productId){
        String imagePath = this.productImageService.getProductImageByProduct(productId);
        return new ResponseEntity<>(imagePath, HttpStatus.OK);
    }

    @PostMapping(value = "add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product){
        ProductDTO productSaved = this.productService.salvarProduto(product);
        return new ResponseEntity<>(productSaved, HttpStatus.OK);
    }

    @PostMapping(value = "{productId}/image/upload")
    public void uploadImage(@RequestParam MultipartFile[] file,
                            @RequestParam(required = false) List<Boolean> imageOrder,
                            @PathVariable Long productId) throws Exception {
        this.productImageService.uploadImage(file, productId, imageOrder);
    }

    @PutMapping(value = "{productId}/update-stock")
    public void updateProductDisponibility(@PathVariable Long productId,
                                           @RequestParam boolean inStock) throws Exception {
        this.productService.updateProductStock(productId, inStock);
    }

    @DeleteMapping(value = "{productId}/remove")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long productId){
        this.productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "images/delete")
    public ResponseEntity<HttpStatus> deleteImages(@RequestBody List<Long> imagesToDelete){
        this.productImageService.deleteImages(imagesToDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
