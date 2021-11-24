package tr.com.english.learnlang.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.service.authanticate.AuthService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("login")
    GeneralResponse handleLogin(@RequestBody Credentials credentials){
        return authService.authanticate(credentials);
    }
    @PostMapping("logout")
    GeneralResponse logout(@RequestHeader("Authorization") String authorization){
        authService.clearToken(authorization);
        return new GeneralResponse("Uygulamadan çıkış yapılmıştır.",true);

    }
}
