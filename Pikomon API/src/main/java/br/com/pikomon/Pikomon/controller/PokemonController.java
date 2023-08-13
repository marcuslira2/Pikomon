package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.CreatePokemonDTO;
import br.com.pikomon.Pikomon.dto.PokemonDTO;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Optional<Pokemon>> listById(@PathVariable Integer id){
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        return new ResponseEntity<>(pokemon,HttpStatus.ACCEPTED);

    }

    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody CreatePokemonDTO dto){
        Pokemon pokemon = pokemonService.create(dto.pokemonId(),dto.pokemonLevel(),dto.trainerName());
        return new ResponseEntity<>(pokemon,HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        pokemonService.deleteById(id);

    }

}
