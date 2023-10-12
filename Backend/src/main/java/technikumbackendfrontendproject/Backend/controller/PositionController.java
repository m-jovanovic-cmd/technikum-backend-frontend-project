package technikumbackendfrontendproject.Backend.controller;

import org.springframework.web.bind.annotation.*;

import technikumbackendfrontendproject.Backend.model.Position;
import technikumbackendfrontendproject.Backend.model.DTO.PositionDTO;
import technikumbackendfrontendproject.Backend.model.Product;
import technikumbackendfrontendproject.Backend.service.PositionService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionService positionService;


    // Constructor
    
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/{id}")
    public List<Position> findUserPositions(@PathVariable Long userId) {
        return (List<Position>) positionService.findByUserId(userId);
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
