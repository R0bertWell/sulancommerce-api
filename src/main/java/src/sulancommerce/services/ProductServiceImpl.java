package src.sulancommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import src.sulancommerce.models.dtos.CategoryDTO;
import src.sulancommerce.models.dtos.ProductDTO;
import src.sulancommerce.models.dtos.ProductSimpleDTO;
import src.sulancommerce.models.dtos.ProductTagDTO;
import src.sulancommerce.models.entities.*;
import src.sulancommerce.repositories.*;
import src.sulancommerce.utils.Utils;

import java.util.*;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductImageRepository productImageRepository;

    private final ProductInfoRepository productInfoRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductCategoryRepository productCategoryRepository,
                              ProductImageRepository productImageRepository,
                              ProductInfoRepository productInfoRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productImageRepository = productImageRepository;
        this.productInfoRepository = productInfoRepository;
    }

    @Override
    public Page<ProductDTO> getProductsByFilters(String search, Long categoryId, boolean inStock, Pageable pageable) throws Exception {
        //String serverBaseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());

        Page<Product> productsPage = this.productRepository.getProductsByFiltersPaged(Utils.treatPercentString(search, true, true), categoryId, inStock, pageable);

        List<ProductDTO> productDTOList = new ArrayList<>();
        productsPage.getContent().forEach( product -> {
            Long productId = product.getProductId();
            List<ProductCategory> productCategories = this.productCategoryRepository.findProductCategoriesByProductId(productId);
            List<ProductImage> productImages = this.productImageRepository.findProductImagesByProductId(productId);

            productDTOList.add(new ProductDTO(product, productCategories, productImages));
        });


        return new PageImpl<>(productDTOList, productsPage.getPageable(), productsPage.getTotalElements());
    }

    @Override
    public List<ProductTagDTO> getProductsByCategories(Pageable pageable) {
        List<Category> categories = this.categoryRepository.getAllCategoriesOrderByNrOrder();
        List<ProductTagDTO> productTagDTOS = new ArrayList<>();
        categories.forEach( cat -> {
            List<Product> products = this.productRepository.getProductsByCategory(cat.getCategoryId());
            if(!products.isEmpty()) {
                List<ProductSimpleDTO> productSimpleDTOS = new ArrayList<>();
                CategoryDTO categoryDTO = new CategoryDTO(cat);
                products.forEach(prod -> {
                    List<ProductImage> productImages = this.productImageRepository.findProductImagesByProductId(prod.getProductId());
                    productSimpleDTOS.add(new ProductSimpleDTO(prod, productImages));
                });
                productTagDTOS.add(new ProductTagDTO(categoryDTO, productSimpleDTOS));
            }
        });
        return productTagDTOS;
    }

    @Override
    public ProductDTO salvarProduto(ProductDTO product) {
        if(product != null) {
            Product productToSave = product.toEntity();
            productToSave = this.productRepository.save(productToSave);
            List<ProductCategory> productCategories = new ArrayList<>();
            if(!product.getCategories().isEmpty()){
                Set<ProductCategory> prodsCatsToSave = new HashSet<>();


                Product finalProductToSave = productToSave;
                product.getCategories().forEach(categoryDTO -> {
                    ProductCategoryPK pk = new ProductCategoryPK();
                    pk.setProduct(finalProductToSave);
                    pk.setCategory(categoryDTO.toEntity());
                    prodsCatsToSave.add(new ProductCategory(pk));
                });

                productCategories = this.productCategoryRepository.saveAll(prodsCatsToSave);
            }

            return new ProductDTO(productToSave, productCategories);
        }
        return new ProductDTO();
    }

    @Override
    public void deleteProduct(Long productId) {
        //this.productCategoryRepository.deleteByProductCategoryId_Product_ProductId(productId);
        //this.productInfoRepository.deleteByProductInfoId_Product_ProductId(productId);
        this.productRepository.deleteById(productId);
    }

    @Override
    public void updateProductStock(Long productId, boolean inStock) {
        Optional<Product> product_opt = this.productRepository.findById(productId);
        product_opt.ifPresent( product -> {
            product.setInStock(inStock);
            this.productRepository.save(product);
        });
    }
}
