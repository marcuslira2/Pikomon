package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.ChangePWDDTO;
import br.com.pikomon.Pikomon.dto.CreateUserDTO;
import br.com.pikomon.Pikomon.dto.UserDTO;
import br.com.pikomon.Pikomon.infra.exceptions.UserBadRequestException;
import br.com.pikomon.Pikomon.infra.exceptions.UserNotFoundException;
import br.com.pikomon.Pikomon.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Validated
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> listAll(){
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserDTO dto) throws UserBadRequestException {
        return userService.save(dto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> updatePWD(@PathVariable Integer id, @RequestBody ChangePWDDTO pwd) throws UserNotFoundException{
        return userService.changePWD(id,pwd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws UserNotFoundException{
        return userService.deleteById(id);

    }
}
