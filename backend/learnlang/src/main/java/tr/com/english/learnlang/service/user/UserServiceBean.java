package tr.com.english.learnlang.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.dao.CategoryDao;
import tr.com.english.learnlang.dao.UserDao;
import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.user.UserInfo;
import tr.com.english.learnlang.entity.words.Word;
import tr.com.english.learnlang.service.user.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceBean implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public GeneralResponse addUser(User user) {
        if(isUsedMailBefore(user.getEmail())){
            userDao.save(user);
            return new GeneralResponse("Kullanıcı kaydi başarıyla gerçekleşti",true,getUserInfo(user));
        }
        return new GeneralResponse("Email daha önce kullanılmış . Lütfen farklı bir e-mail kullanın",false);
    }
    private boolean isUsedMailBefore(String email){
       User user= userDao.getUserByEmail(email);
        return user == null;
    }

    @Override
    public User getUser(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public UserInfo getUserInfo(User user) {
        return new UserInfo(user.getId(),user.getUsername(),user.getEmail(),user.getScore(),user.getKelimeList());
    }
}
