package br.com.pikomon.pikomon.repository;

import br.com.pikomon.pikomon.persistence.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends JpaRepository<Battle,Long> {

    @Query("SELECT b FROM Battle b WHERE b.uuid = :uuid")
    Battle findByuuid(String uuid);
}
