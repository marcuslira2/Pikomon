package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.UserDTO;
import br.com.pikomon.Pikomon.infra.exception.UserNotFoundException;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public List<UserDTO> listAll(){
        return userRepository.findAll().stream()
                .filter(user -> user.getDeleted()==0).toList()
                .stream().map(this::converterToDTO).toList();
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
            user.setCreatedDate(new Date());
            user.setDeleted(0);
            return userRepository.save(user);

        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }


    public Optional<User> findById(Integer id) throws UserNotFoundException {
        return Optional.ofNullable(
                this.userRepository
                        .findByIdAndDeletedFalse(id)
                        .orElseThrow(UserNotFoundException::new)
        );
    }

    public void deleteById(Integer id){
            Optional<User> user = userRepository.findById(id);
            user.get().setDeleted(1);
            user.get().setDeletedDate(new Date());
            userRepository.save(user.get());
    }

    public User changePWD(Integer id,User userObj){
        Optional<User> user = userRepository.findById(id);
        user.get().setPassword(userObj.getPassword());

        return userRepository.save(user.get());
    }

}
