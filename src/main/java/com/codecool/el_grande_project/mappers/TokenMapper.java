package com.codecool.el_grande_project.mappers;

import com.codecool.el_grande_project.DTO.TokenDTO;
import com.codecool.el_grande_project.entity.Token;
import com.codecool.el_grande_project.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenMapper {

    public Token mapDtoToToken(TokenDTO token){
        Jws<Claims> tokenDecodedJws = Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token.token());
        Claims claims = tokenDecodedJws.getBody();
        Date expieryDate = claims.getExpiration();
        return new Token(token.token(),expieryDate);
    }
}
