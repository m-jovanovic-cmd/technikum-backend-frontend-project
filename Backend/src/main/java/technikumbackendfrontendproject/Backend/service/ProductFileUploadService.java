package technikumbackendfrontendproject.Backend.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.repository.ProductFileUploadRepository;

public class ProductFileUploadService {
    
    @Autowired
    private ProductFileUploadRepository productFileUploadRepository;

    public void uploadProductInformation(Product product, MultipartFile file) {
        product.setFilename(StringUtils.cleanPath(file.getOriginalFilename()));
        product.setFiletype(file.getContentType());
        try {
            product.setData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        productFileUploadRepository.save(product);
    }
}