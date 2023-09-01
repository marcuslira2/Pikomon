package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {
}
