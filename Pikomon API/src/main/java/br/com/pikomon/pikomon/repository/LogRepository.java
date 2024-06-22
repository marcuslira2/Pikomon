package br.com.pikomon.pikomon.repository;

import br.com.pikomon.pikomon.persistence.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}
