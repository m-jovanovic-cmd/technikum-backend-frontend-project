/*package technikumbackendfrontendproject.Backend.service;

import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.criteria.Path;

public class StorageService {
    
    private final Path storagePath;

    public StorageService() {
        storagePath = Path.of("uploads/");
    }

    public void store(MultipartFile file) {
        String filename = file.getOriginalFilename();
        Path filePath = storagePath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
*/