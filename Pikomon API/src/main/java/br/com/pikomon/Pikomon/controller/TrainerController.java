package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.TrainerDTO;
import br.com.pikomon.Pikomon.persistence.Trainer;
import br.com.pikomon.Pikomon.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trainer")
public class TrainerController {

    @Autowired
    TrainerService trainerService;

    @PostMapping
    public ResponseEntity<Trainer> create(@RequestBody TrainerDTO trainerDTO){
        Trainer trainer = trainerService.save(trainerDTO.name(),trainerDTO.money(), trainerDTO.pokemonId());
        return new ResponseEntity<>(trainer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Trainer>> listAll(){
        return new ResponseEntity<>(trainerService.listAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Trainer>> findById(@PathVariable Integer id){
        return new ResponseEntity<>(trainerService.findById(id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        trainerService.deleteById(id);
        return new ResponseEntity<>("Trainer deleted",HttpStatus.ACCEPTED);
    }

}
