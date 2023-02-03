package technikumbackendfrontendproject.Backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.DTO.PositionDTO;
import technikumbackendfrontendproject.Backend.service.PositionService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;


    // Constructor
    
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }


    @PostMapping
    @ResponseStatus(code = CREATED)
    public Position createPosition(@RequestBody PositionDTO positionDTO) {
        return positionService.save(fromDTO(positionDTO), 0L, positionDTO.getProductId());
    }

    private static Position fromDTO(PositionDTO positionDTO) {
        return new Position(positionDTO.getId(), positionDTO.getQuantity());
    }
}
