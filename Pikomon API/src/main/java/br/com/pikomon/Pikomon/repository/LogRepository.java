package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
