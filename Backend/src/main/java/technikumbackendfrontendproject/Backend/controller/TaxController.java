package technikumbackendfrontendproject.Backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import technikumbackendfrontendproject.Backend.model.Tax;
import technikumbackendfrontendproject.Backend.repository.TaxRepository;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {
    
    private TaxRepository taxRepository;

    public TaxController(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    /**
     * Retrieve a list of all tax entities in the system.
     *
     * @return A list of Tax entities representing all available tax records in the system.
     */
    @GetMapping
    public List<Tax> findAll() {
        return taxRepository.findAll();
    }

    /**
     * Create a new tax record in the system.
     *
     * @param tax The Tax entity containing the tax information for creation.
     * @return The created Tax entity with an HTTP status code 'Created' indicating a successful creation.
     */
    @PostMapping
    @ResponseStatus(code = CREATED)
    public Tax create(@RequestBody Tax tax) {
        return taxRepository.save(tax);
    }
}
