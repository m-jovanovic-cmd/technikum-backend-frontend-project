package technikumbackendfrontendproject.Backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import technikumbackendfrontendproject.Backend.model.DTO.ProductDTO;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.service.EntityNotFoundException;
import technikumbackendfrontendproject.Backend.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }


    @GetMapping("/{type}")
    public List<Product> findAllProductsByType(@PathVariable String type) {
        return productService.findByType(type);
    }

    @PostMapping
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO productDTO) {
        Product product = productService.save(productDTO.convertToProduct(), productDTO.getTaxId());
        return ResponseEntity.created(URI.create("http://localhost:8080/api/products")).body(product);
    }

    @PutMapping("/setStatus/{id}")
    public Product setStatus(@PathVariable Long id) {
        return productService.setStatus(id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}