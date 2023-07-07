/*package technikumbackendfrontendproject.Backend.controller;

import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import technikumbackendfrontendproject.Backend.service.StorageService;

@RestController
@RequestMapping("/files")
public class FileHandlingController {
    
    private final StorageService storageService;

    public FileHandlingController(StorageService storageService) {
        storagePath = Path.
    }

    @PostMapping()
    public ResponseEntity<String> handleFileUpload() {
        
        return ResponseEntity<String>("ok", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Resource> handleFileDownload()
}*/