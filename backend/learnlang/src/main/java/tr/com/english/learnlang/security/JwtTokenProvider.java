package tr.com.english.learnlang.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${learnlang.app.secret}")
    private String APP_SECRET;
    @Value("${learnlang.app.expires.in}")
    private long EXPIRES_IN;

    public String generateJWTToken(Authentication authentication) {
        JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expiredDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date()).setExpiration(expiredDate).signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }
    public String getUserIdFromJwt(String token){
        Claims claims=extracted(token);
        return claims.getSubject();
    }
    public boolean validateToken(String token){
        try{
            Claims claims=extracted(token);
            System.out.println("validate token :  "+!isTokenExpired(token));
            return !isTokenExpired(token);
        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (UnsupportedJwtException e){
            return false;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Claims claims=extracted(token);
        return claims.getExpiration().before(new Date());


    }

    private Claims extracted(String token) {
        return Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
    }

}
