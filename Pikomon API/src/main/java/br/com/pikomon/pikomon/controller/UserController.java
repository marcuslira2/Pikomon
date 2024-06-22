package br.com.pikomon.pikomon.controller;

import br.com.pikomon.pikomon.dto.user.*;
import br.com.pikomon.pikomon.infra.security.TokenService;
import br.com.pikomon.pikomon.persistence.User;
import br.com.pikomon.pikomon.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Validated
@RestController
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
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.pwd());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.gerarToken((User) authenticate.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listAll() {
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) throws Exception {
        UserDto userDTO = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateUserDto dto) throws Exception {
        try {
        UserDto user = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePWD(@PathVariable Integer id, @RequestBody @Valid ModifyPasswordDto pwd) throws Exception {

        String userName = userService.changePWD(id, pwd);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password changed for user " + userName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) throws Exception {
        String userName = userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userName + " deleted.");

    }
}
