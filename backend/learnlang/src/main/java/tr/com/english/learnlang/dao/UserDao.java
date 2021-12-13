package tr.com.english.learnlang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.english.learnlang.entity.user.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}
