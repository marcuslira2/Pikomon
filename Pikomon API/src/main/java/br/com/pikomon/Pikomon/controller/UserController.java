package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.ChangePWDDTO;
import br.com.pikomon.Pikomon.dto.UserDTO;
import br.com.pikomon.Pikomon.infra.exception.BadRequestCreation;
import br.com.pikomon.Pikomon.infra.exception.UserNotFoundException;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> listAll(){
        List<UserDTO> users = userService.listAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) throws UserNotFoundException {
        Optional<User> user = userService.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.ACCEPTED)).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userObj) throws BadRequestCreation {
        userObj.setCreatedDate(new Date());
        Optional<User> user = userService.save(userObj);
        return user.map(userSaved -> new ResponseEntity<>( userSaved,HttpStatus.CREATED)).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updatePWD(@PathVariable Integer id, @RequestBody ChangePWDDTO pwd) throws UserNotFoundException{
        Optional<User> user = userService.findById(id);
        user.ifPresent(value -> value.setPassword(pwd.password()));
        return new ResponseEntity<>("Password changed",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
