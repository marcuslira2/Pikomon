package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.user.ChangePWDDTO;
import br.com.pikomon.Pikomon.dto.user.CreateUserDTO;
import br.com.pikomon.Pikomon.dto.user.UserDTO;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectBadRequestException;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
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
    public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserDTO dto) throws ObjectBadRequestException {
        return userService.save(dto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<?> updatePWD(@PathVariable Integer id, @RequestBody @Valid ChangePWDDTO pwd) throws ObjectNotFoundException {
        return userService.changePWD(id,pwd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws ObjectNotFoundException {
        return userService.deleteById(id);

    }
}
