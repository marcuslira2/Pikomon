package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Long> {

    @Override
    @Query("SELECT b FROM Pokemon b WHERE b.id = :id and b.deleted !=1")
    Optional<Pokemon> findById(Long id);
}
