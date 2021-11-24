package tr.com.english.learnlang.auth;

public class LoginAuthException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Kullanıcı adı veya şifre hatalıdır.";
    }

    @Override
    public String toString() {
        return "Kullanıcı adı veya şifre hatalıdır";
    }
}
