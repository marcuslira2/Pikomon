package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<Move,Integer> {
}
