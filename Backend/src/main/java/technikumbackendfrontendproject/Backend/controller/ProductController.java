package technikumbackendfrontendproject.Backend.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;
import technikumbackendfrontendproject.Backend.model.Product;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO productDTO) {
        Product product = productService.save(productDTO.convertToProduct(), productDTO.getTaxId());
        return ResponseEntity.created(URI.create("http://localhost:8080/api/products")).body(product);
    }

    @PostMapping("/imageUpload")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String response = productService.uploadImage(file);
                System.out.print(response);
                return response;
            } catch (Exception e) {
                return "Failed to upload the file: " + e.getMessage();
            }
        } else {
            return "File is empty or not provided!";
        }
    }

    @PutMapping("/setStatus/{id}")
    public Product setStatus(@PathVariable Long id) {
        return productService.setStatus(id);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        try {
            System.out.print("In Controller gekommen");
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            System.out.print("In Fehler gekommen");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}