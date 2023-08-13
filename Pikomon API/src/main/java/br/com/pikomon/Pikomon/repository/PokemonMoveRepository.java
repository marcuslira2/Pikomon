package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.PokemonMove;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonMoveRepository extends JpaRepository<PokemonMove,Integer> {
}
