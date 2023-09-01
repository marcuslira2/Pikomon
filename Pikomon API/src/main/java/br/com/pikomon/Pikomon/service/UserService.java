package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.UserDTO;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public List<UserDTO> listAll(){
        List<User> users = userRepository.findAll();

        return users.stream().map(this::converterToDTO).collect(Collectors.toList());

    }

    private UserDTO converterToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getTrainers()
        );
    }

    public User save(User userObj){
        try{
            User user = new User();
            user.setLogin(userObj.getLogin());
            user.setName(userObj.getName());
            user.setPassword(userObj.getPassword());
            return userRepository.save(user);

        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

    public void deleteById(Integer id){ userRepository.deleteById(id);}

    public User changePWD(Integer id,User userObj){
        Optional<User> user = userRepository.findById(id);
        user.get().setPassword(userObj.getPassword());

        return userRepository.save(user.get());
    }

}
