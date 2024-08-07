package br.com.pikomon.pikomon.controller;

import br.com.pikomon.pikomon.dto.pokemon.CreatePokemonDTO;
import br.com.pikomon.pikomon.dto.pokemon.PokemonDTO;
import br.com.pikomon.pikomon.persistence.Pokemon;
import br.com.pikomon.pikomon.service.pokemon.PokemonService;
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
    public ResponseEntity<PokemonDTO> findById(@PathVariable Long id)  {
        PokemonDTO dto = pokemonService.findDTOById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody CreatePokemonDTO dto) {
        Pokemon pokemon = pokemonService.save(dto.id(),dto.level(),dto.trainerUUID());
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);

    }

    @DeleteMapping(path = "/{id}")

    public ResponseEntity<String> deleteById(@PathVariable Long id){
        String pokemonName = pokemonService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Good Bye " + pokemonName);
    }

}
