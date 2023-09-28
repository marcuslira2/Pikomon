package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.ChangePWDDTO;
import br.com.pikomon.Pikomon.dto.CreateUserDTO;
import br.com.pikomon.Pikomon.dto.UserDTO;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectBadRequestException;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    public List<UserDTO> listAll(){
        log.info("Listing all users");
        return userRepository.findAll().stream()
                .filter(User::isEnabled).toList()
                .stream().map(this::converterToDTO).toList();
    }

    private UserDTO converterToDTO(User user) {
        log.info("Converting user into DTO");
        return new UserDTO(
                user.getId(),
                user.getLogin()
        );
    }

    public ResponseEntity<?> save(CreateUserDTO dto) throws ObjectBadRequestException {
        User user = new User();
        log.info("Saving user: "+ dto.name());
        user.setLogin(dto.login());
        user.setPassword(dto.password());
        this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User saved.");
    }

    public ResponseEntity<?> findById(Integer id) throws ObjectNotFoundException {
        User user = userRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        log.info("Searching user...");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    public ResponseEntity<?> deleteById(Integer id) throws ObjectNotFoundException {
        User user = userRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        log.info("Deleting user...");
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User was deteled.");
    }

    public ResponseEntity<?> changePWD(Integer id, ChangePWDDTO pwddto) throws ObjectNotFoundException {
        User user = userRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        log.info("Updating user...");
        user.setPassword(pwddto.password());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password was changed.");
    }

}
