package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    @Autowired
    Optional<Trainer> findByIdAndDeletedFalse(Integer id);

    @Query("SELECT t FROM Trainer t WHERE t.name = :name")
    Optional<Trainer> findByName(String name);

}
