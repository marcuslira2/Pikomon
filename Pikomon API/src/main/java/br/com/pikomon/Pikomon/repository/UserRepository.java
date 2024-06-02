package br.com.pikomon.Pikomon.repository;

import br.com.pikomon.Pikomon.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    UserDetails findByLogin(String login);
}
