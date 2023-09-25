package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon,Long> {
}
