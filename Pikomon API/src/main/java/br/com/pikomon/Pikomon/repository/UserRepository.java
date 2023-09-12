package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Integer> {

    @Autowired
    Optional<User> findByIdAndDeletedFalse(Integer id);

}
