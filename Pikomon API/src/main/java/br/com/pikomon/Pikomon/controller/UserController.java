package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.ChangePWDDTO;
import br.com.pikomon.Pikomon.dto.UserDTO;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userObj){
        userObj.setCreatedDate(new Date());
        User user = userService.save(userObj);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updatePWD(@PathVariable Integer id, @RequestBody ChangePWDDTO pwd){
        Optional<User> user = userService.findById(id);
        user.ifPresent(value -> value.setPassword(pwd.password()));
        return new ResponseEntity<>("Password changed",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("User deleted",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
