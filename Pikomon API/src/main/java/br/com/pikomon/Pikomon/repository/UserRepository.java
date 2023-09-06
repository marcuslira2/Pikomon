package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository  extends JpaRepository<User,Integer> {

}
