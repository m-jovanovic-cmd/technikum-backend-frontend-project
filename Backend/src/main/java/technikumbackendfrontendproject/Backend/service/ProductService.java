package technikumbackendfrontendproject.Backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
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
        try {
            return productRepository.findById(id);
        } catch(ObjectNotFoundException e) {
            throw new RuntimeException("no user for this id: "+ id);
        }
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
        destination = destination.substring(0, destination.length() - "/Backend".length()) + "/Uploads/images";
        File directory = new File(destination);
        directory.mkdirs();
        var fileName = file.getOriginalFilename();
        Path uploadPath = Paths.get(destination, fileName);

        try {
            file.transferTo(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int index = destination.indexOf("/Uploads/");

        String result = "";
        if (index != -1) {
            result = destination.substring(index);
        } else {
            System.out.println("Substring not found.");
        }
        var finalDestination = result + "/" + fileName;

        return finalDestination;
    }

    public Product getProduct(Long id) {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Product product2 = product.get();
        return product2;
    }
}