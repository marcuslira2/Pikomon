package br.com.pikomon.Pikomon.schedule;


import br.com.pikomon.Pikomon.modal.AbilityData;
import br.com.pikomon.Pikomon.modal.MoveData;
import br.com.pikomon.Pikomon.modal.PokemonData;
import br.com.pikomon.Pikomon.modal.StatData;
import br.com.pikomon.Pikomon.persistence.Ability;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.PokemonMove;
import br.com.pikomon.Pikomon.repository.AbilityRepository;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AbilityAutoCreationScheduler {

    private final AbilityRepository abilityRepository;

    private final PokemonRepository pokemonRepository;

    private final RestTemplate restTemplate;

    private static final Logger log = LoggerFactory.getLogger(AbilityAutoCreationScheduler.class);

    public AbilityAutoCreationScheduler(AbilityRepository abilityRepository, PokemonRepository pokemonRepository, RestTemplate restTemplate) {
        this.abilityRepository = abilityRepository;
        this.pokemonRepository = pokemonRepository;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void createAbility() {
        log.info("Creating pokemons on database");
        for (int i = 1; i < 81; i++) {

            AbilityData abilityData = restTemplate.getForObject("https://pokeapi.co/api/v2/ability/" + i, AbilityData.class);
            assert abilityData!=null;
            Ability ability = new Ability();
            ability.setName(abilityData.getName());
            ability.setEffect(abilityData.getEffect_entries().get(1).getShort_effect());
            log.info("Saving ability: "+abilityData.getName());
            abilityRepository.save(ability);
        }
    }
}
