package tr.com.english.learnlang.service.authanticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.auth.Credentials;
import tr.com.english.learnlang.auth.LoginAuthException;
import tr.com.english.learnlang.auth.LoginResponse;
import tr.com.english.learnlang.dao.UserDao;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.service.user.UserService;

import java.util.UUID;

@Service
public class AuthServiceBean implements AuthService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse authanticate(Credentials credentials) {
        User user= userDao.getUserByEmail(credentials.getEmail());
        if(user !=null){
            boolean passwordMatched = credentials.getPassword().equals(user.getPassword());
            if(passwordMatched){
                LoginResponse loginResponse=new LoginResponse("Giriş Başarılı...",true,userService.getUserInfo(user));
                return loginResponse;
            }else {
                throw new LoginAuthException();
            }

        }else {
            throw new LoginAuthException();
        }
    }
}
