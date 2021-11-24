package tr.com.english.learnlang.service.authanticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.auth.Credentials;
import tr.com.english.learnlang.auth.LoginAuthException;
import tr.com.english.learnlang.auth.LoginResponse;
import tr.com.english.learnlang.auth.Token;
import tr.com.english.learnlang.dao.TokenDao;
import tr.com.english.learnlang.dao.UserDao;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.service.user.UserService;

import java.util.UUID;

@Service
public class AuthServiceBean implements AuthService{

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Override
    public LoginResponse authanticate(Credentials credentials) {
        User user= userDao.getUserByEmail(credentials.getEmail());
        if(user !=null){
            boolean passwordMatched = credentials.getPassword().equals(user.getPassword());
            if(passwordMatched){
                String token = generationRandomToken();
                Token tokenObj=new Token();
                tokenObj.setToken(token);
                tokenObj.setUser(user);
                tokenDao.save(tokenObj);
                LoginResponse loginResponse=new LoginResponse("Giriş Başarılı...",true,userService.getUserInfo(user),token);
                return loginResponse;
            }else {
                throw new LoginAuthException();
            }

        }else {
            throw new LoginAuthException();
        }
    }

    @Override
    public void clearToken(String authorization) {
        tokenDao.deleteById(authorization);
    }

    @Override
    public String generationRandomToken() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
