package tr.com.english.learnlang.service.user_detail_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.dao.UserDao;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.security.JwtUserDetails;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(username);
        if (user != null) {
            return JwtUserDetails.userToJwtUser(user);
        }
        return null;
    }
    public UserDetails loadUserById(Long id){
        User user=userDao.getById(id);
        return JwtUserDetails.userToJwtUser(user);
    }
}
