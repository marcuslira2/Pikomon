package br.com.pikomon.pikomon;

import br.com.pikomon.pikomon.dto.user.CreateUserDto;
import br.com.pikomon.pikomon.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class UserTest {


    @MockBean
    private UserService service;

    @Test
    void create_new_user(){
        CreateUserDto user = new CreateUserDto("Marcus","12345");
        Assertions.assertNull(service.save(user));
    }




}
