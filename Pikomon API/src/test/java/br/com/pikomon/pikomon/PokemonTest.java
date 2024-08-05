package br.com.pikomon.pikomon;

import br.com.pikomon.pikomon.persistence.User;
import br.com.pikomon.pikomon.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
class PokemonTest {

	@MockBean
	private UserRepository userRepository;


    @Test
	public void create_new_user(){
		System.out.println("ola mundo");

		User u1 = new User();
		u1.setLogin("Marcuskk");
		u1.setPassword("12345kk");
		u1.setUuid(String.valueOf(UUID.randomUUID()));
		u1.setCreatedDate(new Date());
		userRepository.save(u1);
		System.out.println("funcionou?!");
	}


}
