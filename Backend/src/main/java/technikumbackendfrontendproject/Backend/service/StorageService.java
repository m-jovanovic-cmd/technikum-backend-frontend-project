package technikumbackendfrontendproject.Backend.service;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    private final Path storagePath;

    public StorageService() {
        storeagePath = Path.of("uploads/");
    }

    public void store(MultipartFile file) {
        String filename = file.getOriginalFilename();
        Path filePath = storagePath.resolve(filename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING)

    }

    public Resource serve(String filename) {
        Path filePath = storagePath.resolve(filename);
        Resource resource;

        try {

            resource = new UrlResource(filePath.toUri());
            return resource;
        } catch (MalformedURLException e) {
            throw new IOException
        }
    }
    

