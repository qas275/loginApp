package DxcApplication.LoginApp.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import DxcApplication.LoginApp.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET}")
    private String JWT_SECRET; 

    private Key getSigningKey(){
        System.out.println(JWT_SECRET);
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());
        return doGenerateToken(claims, user.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        System.out.println("generating");
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        System.out.println("generated");
        System.out.println(jwt);
        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token).getBody();
        return claims.get("username", String.class);
    }

    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token).getBody();
        return claims.get("role", String.class);
    }
}
