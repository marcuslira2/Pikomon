package br.com.pikomon.Pikomon.controller;

import br.com.pikomon.Pikomon.dto.user.*;
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
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.pwd());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.gerarToken((User) authenticate.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new tokenDTO(token));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listAll() {
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) throws Exception {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid CreateUserDTO dto) throws Exception {
        UserDTO user = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePWD(@PathVariable Integer id, @RequestBody @Valid ModifyPasswordDTO pwd) throws Exception {

        String userName = userService.changePWD(id, pwd);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password changed for user " + userName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws Exception {
        String userName = userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userName + " deleted.");

    }
}
