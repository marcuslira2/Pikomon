package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.user.*;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectBadRequestException;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
import br.com.pikomon.Pikomon.infra.security.TokenService;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Validated
@Controller
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.pwd());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.gerarToken((User) authenticate.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new tokenDTO(token));
    }

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
    public  ResponseEntity<?> updatePWD(@PathVariable Integer id, @RequestBody @Valid ModifyPasswordDTO pwd) throws ObjectNotFoundException {
        return userService.changePWD(id,pwd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) throws ObjectNotFoundException {
        return userService.deleteById(id);

    }
}
