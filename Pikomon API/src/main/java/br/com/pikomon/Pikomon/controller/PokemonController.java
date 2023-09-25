package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.CreatePokemonDTO;
import br.com.pikomon.Pikomon.dto.PokemonDTO;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectBadRequestException;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.service.PokemonService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> listAll(){

        return new ResponseEntity<>(pokemonService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {
        return pokemonService.findById(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody CreatePokemonDTO dto) throws ObjectBadRequestException {
        Pokemon pokemon = pokemonService.save(dto.id(),dto.level(),dto.trainer());
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);

    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws ObjectNotFoundException{
        return pokemonService.deleteById(id);

    }

}
