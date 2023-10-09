package br.com.pikomon.Pikomon.infra.security;

import br.com.pikomon.Pikomon.persistence.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


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
                    .withExpiresAt(loginTimeExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error: ",exception);
        }
    }

    public String getSubject(String tokenJWT){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Pikomon API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        }catch (JWTCreationException e){
            throw new RuntimeException("Token JWT expirado");
        }

    }

    private Instant loginTimeExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
