package br.com.pikomon.pikomon.controller;

import br.com.pikomon.pikomon.dto.trainer.CreateTrainerDTO;
import br.com.pikomon.pikomon.dto.trainer.TrainerDTO;
import br.com.pikomon.pikomon.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<TrainerDTO> findById(@PathVariable Integer id) {
        TrainerDTO trainerDTO = trainerService.findDTOById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(trainerDTO);
    }

    @PostMapping
    public ResponseEntity<TrainerDTO> create(@RequestBody @Valid CreateTrainerDTO trainerDTO){
        TrainerDTO dto = trainerService.save(trainerDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id)  {
        String trainerName = trainerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bye "+trainerName);
    }

}
