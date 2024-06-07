package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.trainer.CreateTrainerDTO;
import br.com.pikomon.Pikomon.dto.trainer.TrainerDTO;
import br.com.pikomon.Pikomon.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {


    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public ResponseEntity<List<TrainerDTO>> listAll() {
        return new ResponseEntity<>(trainerService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TrainerDTO> findById(@PathVariable Integer id) throws Exception {
        TrainerDTO trainerDTO = trainerService.findDTOById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainerDTO);
    }

    @PostMapping
    public ResponseEntity<TrainerDTO> create(@RequestBody @Valid CreateTrainerDTO trainerDTO) throws Exception {
        TrainerDTO dto = trainerService.save(trainerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws Exception {
        String trainerName = trainerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bye "+trainerName);
    }

}
