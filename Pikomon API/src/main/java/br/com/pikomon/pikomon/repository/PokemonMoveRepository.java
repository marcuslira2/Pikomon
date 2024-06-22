package br.com.pikomon.pikomon.repository;

import br.com.pikomon.pikomon.persistence.PokemonMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonMoveRepository extends JpaRepository<PokemonMove,Integer> {
}
