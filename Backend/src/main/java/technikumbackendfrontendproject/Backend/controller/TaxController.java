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


    @GetMapping
    public List<Tax> findAll() {
        return taxRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public Tax create(@RequestBody Tax tax) {
        return taxRepository.save(tax);
    }
}
