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
            System.out.println("token : "+token);
            Claims claims=extracted(token);
            System.out.println("validate token :  "+!isTokenExpired(token));
            return !isTokenExpired(token);
        }catch (SignatureException e){
            System.out.println("sorun1"+e.getLocalizedMessage());
            return false;
        }catch (MalformedJwtException e){
            System.out.println("sorun2"+e.getLocalizedMessage());
            return false;
        }catch (ExpiredJwtException e){
            System.out.println("sorun3"+e.getLocalizedMessage());
            return false;
        }catch (UnsupportedJwtException e){
            System.out.println("sorun3");
            return false;
        }catch (IllegalArgumentException e){
            System.out.println("sorun5");
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
