package tr.com.english.learnlang.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.entity.responseEntity.ResponseAuth;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.user.UserInfo;
import tr.com.english.learnlang.security.JwtTokenProvider;
import tr.com.english.learnlang.service.authanticate.AuthService;
import tr.com.english.learnlang.service.user.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
@Slf4j
@RequiredArgsConstructor
public class AuthController {


    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;


    @PostMapping("login")
    ResponseAuth handleLogin(@RequestBody Credentials credentials) {
        try {
            log.info("handle login with credential : {}", credentials);
            UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(credentials.getEmail(),credentials.getPassword());
              Authentication auth = authenticationManager.authenticate(token);
              SecurityContextHolder.getContext().setAuthentication(auth);
              String jwtToken=jwtTokenProvider.generateJWTToken(auth);
              UserInfo userInfo = userService.getUserInfo(userService.getUserByEmail(credentials.getEmail()));
              return new ResponseAuth("Bearer "+jwtToken,true,userInfo);
        } catch (Exception e) {
            log.info("crashed : exception : {}", e.getMessage());
            return new ResponseAuth("Giriş başarısız.", false);
        }

    }



    @PostMapping("signup")
    public GeneralResponse addUser(@RequestBody User user) {
        try {
            GeneralResponse generalResponse = userService.addUser(user);
            if (generalResponse.getSuccessful()) {
                userService.addRoleToUser(user.getEmail(), "ROLE_USER");
            }
            return generalResponse;
        } catch (Exception e) {
            return new GeneralResponse("giriş başarısız.", false);
        }
    }

    @PostMapping("logout")
    GeneralResponse logout() {
        return new GeneralResponse("Uygulamadan çıkış yapılmıştır.", true);

    }
}
