package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.PokemonMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonMoveRepository extends JpaRepository<PokemonMove,Integer> {
}
