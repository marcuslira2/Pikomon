package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.trainer.CreateTrainerDTO;
import br.com.pikomon.Pikomon.dto.trainer.TrainerDTO;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectBadRequestException;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
import br.com.pikomon.Pikomon.service.TrainerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    TrainerService trainerService;

    @GetMapping
    public ResponseEntity<List<TrainerDTO>> listAll(){
        return new ResponseEntity<>(trainerService.listAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {
        return trainerService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateTrainerDTO trainerDTO) throws ObjectBadRequestException {
        return trainerService.save(trainerDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) throws ObjectNotFoundException{
        return trainerService.deleteById(id);
    }

}
