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

    /**
     * Retrieve a list of all products in the system.
     *
     * @return A list of Product entities representing all products available in the system.
     */
    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    /**
     * Retrieve a list of products by their type.
     *
     * @param type The type of products to be retrieved from the Frontend.
     * @return A list of Product entities matching the specified type, extracted from the productService.
     */
    @GetMapping("/{type}")
    public List<Product> findAllProductsByType(@PathVariable String type) {
        return productService.findByType(type);
    }

    /**
     * Create a new product in the system.
     * This method is restricted to users with 'ADMIN' role.
     *
     * @param productDTO The ProductDTO containing the product information for creation.
     * @return ResponseEntity containing the created Product and an HTTP status code (Created) indicating a successful creation.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> create(@RequestBody @Valid ProductDTO productDTO) {
        Product product = productService.save(productDTO.convertToProduct(), productDTO.getTaxId());
        return ResponseEntity.created(URI.create("http://localhost:8080/api/products")).body(product);
    }

    /**
     * Upload an image file for a product.
     * This method is restricted to users with 'ADMIN' role.
     * It returns a response with HTTP status 'Created' upon successful upload.
     *
     * @param file The image file to be uploaded.
     * @return A string response indicating the result of the image upload, including success or failure messages.
     */
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

    /**
     * Update the status of a product by its unique identifier.
     *
     * @param id The unique identifier of the product to update, id is retrieved from Frontend.
     * @return The updated Product entity.
     */
    @PutMapping("/setStatus/{id}")
    public Product setStatus(@PathVariable Long id) {
        return productService.setStatus(id);
    }

    /**
     * Find a product by its unique identifier.
     *
     * @param id The unique identifier of the product to be retrieved from Frontend.
     * @return ResponseEntity containing the found Product if it exists, or a 'Not Found' response if the product is not found.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> saveEditedProduct(@RequestBody ProductDTO productDTO) {
        Product newProduct = productService.save(productDTO.convertToProductWithId(), productDTO.getTaxId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}