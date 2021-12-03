package tr.com.english.learnlang.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.english.learnlang.constant.GeneralResponse;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginResponse extends GeneralResponse {


    public LoginResponse(String message, Boolean successful, Object element) {
        super(message, successful, element);
    }
}
