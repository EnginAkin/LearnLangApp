package tr.com.english.learnlang.entity.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.english.learnlang.entity.user.UserInfo;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAuth {
    private String message ;
    private Boolean successful;
    private UserInfo userInfo;

    public ResponseAuth(String message, Boolean successful) {
        this.message = message;
        this.successful = successful;
    }
}
