package com.hridoykrisna.stdapi.filter;

import com.hridoykrisna.stdapi.dto.UserPrincipal;
import com.hridoykrisna.stdapi.utli.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.UUID;

@Configuration
public class JwtTokenProvider {
    private String secret_key = "Secret_Key_Here";
    private long expiration_hour = Long.valueOf("5");
    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date date = new Date();

        return Jwts.builder().setId(UUID.randomUUID().toString())
                .claim("username", userPrincipal.getUsername())
                .setSubject(String.valueOf(userPrincipal.getId()))
                .setIssuedAt(date).setExpiration(DateUtils.getExpirationTime(expiration_hour))
                .signWith(SignatureAlgorithm.ES512, secret_key)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret_key).parseClaimsJws(token).getBody();
        return (String) claims.get("username");
    }
  public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret_key).parseClaimsJws(token).getBody();
        return Long.valueOf(claims.getSubject());
    }

    public boolean isValidate(String token){
        try {
            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
