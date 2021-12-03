package tr.com.english.learnlang.service.user;

import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.user.UserInfo;

import java.util.List;

public interface UserService {
    GeneralResponse addUser(User user);
    User getUser(Long id);
    List<User> getUsers();
    UserInfo getUserInfo(User user);
    void addRoleToUser(String email,String roleName);

}
