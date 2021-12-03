package tr.com.english.learnlang.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.service.authanticate.AuthService;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;



    @PostMapping("login")
    ResponseEntity<GeneralResponse> handleLogin(@RequestBody Credentials credentials){
          try {
              log.info("handle login with credential : {}",credentials);
              return ResponseEntity.ok().body(authService.authanticate(credentials));
          }catch (Exception e){
              log.info("crashed : exception : {}",e.getMessage());
                return ResponseEntity.badRequest().build();
          }

    }
    @PostMapping("logout")
    GeneralResponse logout(){
        return new GeneralResponse("Uygulamadan çıkış yapılmıştır.",true);

    }
}
