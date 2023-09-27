package technikumbackendfrontendproject.Backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.repository.ProductRepository;
import technikumbackendfrontendproject.Backend.repository.TaxRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final TaxRepository taxRepository;
    
    public ProductService(ProductRepository productRepository, TaxRepository taxRepository) {
        this.productRepository = productRepository;
        this.taxRepository = taxRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByType(String type) {
        return productRepository.findByType(type);
    }

    public Product save(Product product) {
        product.setStatus(true);
        return productRepository.save(product);
    }

    public Product save(Product product, Long taxId) {
        var tax = taxRepository.findById(taxId);

        if (tax.isEmpty()) {
            throw new EntityNotFoundException();
        }

        product.setTax(tax.get());
        product.setStatus(true);
        return save(product);
    }

    public Product setStatus(Long id) {
        var product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Product product2 = product.get();
        product2.setStatus(true);
        return save(product2);
    }

    public String uploadImage(MultipartFile file) {
        String destination = Paths.get("").toAbsolutePath().toString();
        destination = destination.substring(0, destination.length() - "/technikum-backend-frontend-project/Backend".length()) + "/data/images/products";
        File directory = new File(destination);
        directory.mkdirs();
        var fileName = file.getOriginalFilename();
        Path uploadPath = Paths.get(destination, fileName);
        var finalDestination = destination+ "/" + fileName;
        try {
            file.transferTo(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return finalDestination;
    }

    public Product getProduct(Long id) {
        System.out.print("In Service gekommen");
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            System.out.print("Rückgabe war leer?");
            throw new EntityNotFoundException();
        }
        Product product2 = product.get();
        return product2;
    }
}