package tr.com.english.learnlang.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tr.com.english.learnlang.security.JwtTokenProvider;
import tr.com.english.learnlang.service.user_detail_service.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class JwtAuthenticationFilter extends OncePerRequestFilter {

     @Autowired
     private JwtTokenProvider jwtTokenProvider;

     @Autowired
     private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
         try {
             String jwtToken=extractJwtFromRequest(request);
             System.out.println("jwt token :Ç "+jwtToken);
             if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)){
                 System.out.println("girdim 1");

                 String name = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                 UserDetails user = userDetailsService.loadUserByUsername(name);
                 System.out.println("username : "+user.getUsername());
                if(user !=null){
                    System.out.println("girdim 2");
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
             }else{
                 System.out.println("girdim 3");

             }
         }catch (Exception e){
             System.out.println("hata ile karşılatıl JWTTOKENAUThantication filter" +e.getLocalizedMessage());
             return;
         }
         filterChain.doFilter(request,response);
    }


    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearer=request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            System.out.println("girdim");
            return bearer.substring("Bearer".length() + 1);
        }
        return null;
    }
}