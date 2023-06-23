package technikumbackendfrontendproject.Backend.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import technikumbackendfrontendproject.Backend.service.StorageService;

@RestController
@RequestMapping
public class FileHandlingController {

    private final StorageService storageService;

    public FileHandlingController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping
    public String handleFileUpload(@RequestParam("File")MultipartFile file) throws IOException{

        storageService.store(file);
        return "ok";

    }

    @GetMapping
    public ResponseEntity<Resource> handleFileDownload(@RequestParam("filename") String fileString) {
        Resource file = storageService.serve(filename);
        return ResponseEntity.ok()
                .body(file);
    }

    public Resource serve(String filename) {
        Resource resource;

    }
}
