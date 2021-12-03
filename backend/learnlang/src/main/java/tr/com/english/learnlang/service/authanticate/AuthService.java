package tr.com.english.learnlang.service.authanticate;

import tr.com.english.learnlang.auth.Credentials;
import tr.com.english.learnlang.auth.LoginResponse;

public interface AuthService {

     LoginResponse authanticate(Credentials credentials);


}
