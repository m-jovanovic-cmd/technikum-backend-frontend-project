package technikumbackendfrontendproject.Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.service.ProductFileUploadService;

@RestController
@RequestMapping("/product")
public class ProductFileUploadController {

    @Autowired
    private ProductFileUploadService productFileUploadService;

    @PostMapping(value = "/upload", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Long> uploadProductInformation(@RequestBody Product product, @RequestBody MultipartFile file) {
        productFileUploadService.uploadProductInformation(product, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}