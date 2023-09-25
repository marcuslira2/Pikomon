package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon,Long> {
}
