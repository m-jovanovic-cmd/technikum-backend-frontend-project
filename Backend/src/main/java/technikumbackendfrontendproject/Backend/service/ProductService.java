package technikumbackendfrontendproject.Backend.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

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

    public Product findById(Long id) {
        try {
            return productRepository.findById(id).get();
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