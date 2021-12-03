package tr.com.english.learnlang.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.dao.CategoryDao;
import tr.com.english.learnlang.dao.RoleDao;
import tr.com.english.learnlang.dao.UserDao;
import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.role.Role;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.user.UserInfo;
import tr.com.english.learnlang.entity.words.Word;
import tr.com.english.learnlang.service.user.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceBean implements UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);
        if(user==null){
            log.info("user not found in database  {} ",email);
            throw new UsernameNotFoundException("user not found in database");
        }else{
            log.info("user  found in database  {} ",email);

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

    @Override
    public GeneralResponse addUser(User user) {
        if(isUsedMailBefore(user.getEmail())){
            user.setPassword(passwordEncoder.encode(user.getPassword())); // save user with encoded password
            userDao.save(user);
            log.info("Saving new user to database ");
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
        log.info("Get  user  by id to database ");
        return userDao.getById(id);
    }

    @Override
    public List<User> getUsers() {
        log.info("Get  all user  in  database ");
        return userDao.findAll();
    }

    @Override
    public UserInfo getUserInfo(User user) {
        log.info("convert user to user ınfo for fronted  ");
        return new UserInfo(user.getUsername(),user.getEmail());
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("add  role {} to user {}  " ,roleName,email);
        User user = userDao.getUserByEmail(email);
        user.setRoles(new ArrayList<>());
        Role role = roleDao.findByName(roleName);
        user.getRoles().add(role);
    }



}
