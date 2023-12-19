package src.sulancommerce.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import src.sulancommerce.models.entities.Product;
import src.sulancommerce.models.entities.ProductImage;
import src.sulancommerce.repositories.ProductImageRepository;
import src.sulancommerce.repositories.ProductRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService {
    private static final String IMAGE_PATH = "src/main/resources/static/images/";
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    public ProductImageServiceImpl(ProductImageRepository productImageRepository,
                                   ProductRepository productRepository) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
    }

    private String storeImage(MultipartFile file, String imageName) throws Exception {
        try {
            Path imagePath = Paths.get(IMAGE_PATH);

            if(!Files.exists(imagePath)){
                Files.createDirectories(imagePath);
            }

            String extensao = obterExtensaoArquivo(Objects.requireNonNull(file.getOriginalFilename()));
            imageName = imageName + "."+extensao;
            Path imageUpload = imagePath.resolve(imageName);

            Files.write(imageUpload, file.getBytes());
            return imageName;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private String obterExtensaoArquivo(String nomeArquivo) {
        int ultimoPonto = nomeArquivo.lastIndexOf(".");
        if (ultimoPonto != -1 && ultimoPonto < nomeArquivo.length() - 1) {
            return nomeArquivo.substring(ultimoPonto + 1);
        }
        return "";
    }

    private String obterNomeArquivo(String nomeArquivo) {
        int ultimoPonto = nomeArquivo.lastIndexOf(".");
        if (ultimoPonto != -1 && ultimoPonto < nomeArquivo.length() - 1) {
            return nomeArquivo.substring(0, ultimoPonto);
        }
        return "imagemSemNome";
    }

    @Override
    public void uploadImage(MultipartFile[] files, Long productId, List<Boolean> mainImage) throws Exception {
        Optional<Product> productOpt = this.productRepository.findById(productId);
        if(productOpt.isPresent()) {
            Product product = productOpt.get();
            List<ProductImage> productImages = new ArrayList<>();

            for (int i = 0; i < files.length; i++) {
                String prodImageName = "product_"+ obterNomeArquivo(Objects.requireNonNull(files[i].getOriginalFilename())) + "_" + product.getProductId();
                try {
                    String imagePathImgur = this.uploadToImgur(files[i], prodImageName);
                    //String imagePath = "images/" + this.storeImage(files[i], prodImageName);
                    productImages.add(new ProductImage(product, imagePathImgur, mainImage.get(i)));
                } catch (Exception ignored){}
            }

            //String imagePath = "images/" + this.storeImage(file, prodImageName);
            //ProductImage productImage = new ProductImage(product, imagePath, mainImage);
            this.productImageRepository.saveAll(productImages);
        }
    }

    private String uploadToImgur(MultipartFile file, String imageName) throws Exception {
        String imgurApiUrl = "https://api.imgur.com/3/image/";
        String imgLink = "";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Client-ID 1f83eb04ba3b8c7");

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        formData.add("image", resource);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(imgurApiUrl, requestEntity, String.class);

            String responseBody = responseEntity.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            imgLink = jsonNode.path("data").path("link").asText();

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return imgLink;
    }

    @Override
    public String getProductImageByProduct(Long productId) {
        List<String> paths = this.productImageRepository.getImagePathByProductId(productId);
        if(!paths.isEmpty()){
            return paths.get(0);
        }
        return "imageteste.png";
    }

    @Override
    public void deleteImages(List<Long> imagesToDelete) {
        this.productImageRepository.deleteAllById(imagesToDelete);
    }

}
