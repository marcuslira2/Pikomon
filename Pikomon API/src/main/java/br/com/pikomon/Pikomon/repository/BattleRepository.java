package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends JpaRepository<Battle,Long> {
}
