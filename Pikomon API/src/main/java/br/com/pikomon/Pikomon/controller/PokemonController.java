package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.pokemon.CreatePokemonDTO;
import br.com.pikomon.Pikomon.dto.pokemon.PokemonDTO;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.service.pokemon.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> listAll(){

        return new ResponseEntity<>(pokemonService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PokemonDTO> findById(@PathVariable Long id) throws Exception {
        PokemonDTO dto = pokemonService.findDTOById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody CreatePokemonDTO dto) throws Exception {
        Pokemon pokemon = pokemonService.save(dto.id(),dto.level(),dto.trainerUUID());
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);

    }

    @DeleteMapping(path = "/{id}")

    public ResponseEntity<String> deleteById(@PathVariable Long id) throws Exception{
        String pokemonName = pokemonService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Good Bye " + pokemonName);
    }

}
