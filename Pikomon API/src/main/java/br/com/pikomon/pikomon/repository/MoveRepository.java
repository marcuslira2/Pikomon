package br.com.pikomon.pikomon.repository;

import br.com.pikomon.pikomon.persistence.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<Move,Integer> {

    @Query("SELECT m FROM Move m WHERE m.name = :name")
    Move findByName(String name);
}
