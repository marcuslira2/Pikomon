package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move,Integer> {
}
