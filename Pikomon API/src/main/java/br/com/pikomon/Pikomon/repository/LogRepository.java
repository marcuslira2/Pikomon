package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
