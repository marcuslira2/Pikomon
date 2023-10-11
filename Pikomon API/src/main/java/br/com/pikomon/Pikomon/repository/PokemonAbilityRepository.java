package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.PokemonAbility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonAbilityRepository extends JpaRepository<PokemonAbility,Integer> {
}
