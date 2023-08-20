package technikumbackendfrontendproject.Backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/public")
public class PublicRessourceController {

    @GetMapping("/components/{componentName}")
    public ResponseEntity<Resource> loadComponent(@PathVariable String componentName) {
        String path = "static/components/" + componentName; // Adjust the path based on your project structure
        Resource resource = new ClassPathResource(path);

        if (resource.exists()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML) // Adjust the content type as needed
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}