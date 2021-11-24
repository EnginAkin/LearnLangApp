package tr.com.english.learnlang.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.english.learnlang.constant.GeneralResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends GeneralResponse {

    private String token ;

    public LoginResponse(String message, Boolean successful, Object element, String token) {
        super(message, successful, element);
        this.token = token;
    }
}
