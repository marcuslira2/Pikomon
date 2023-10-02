package br.com.pikomon.Pikomon.infra.security;

import br.com.pikomon.Pikomon.persistence.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Pikomon API")
                    .withSubject(user.getLogin())
                    //.withExpiresAt(new Date().toInstant().plus(2 ,2))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error: ",exception);
        }
    }

//    private Instant loginTimeExpiration(){
//        return new LocalDateTime
//    }
}
