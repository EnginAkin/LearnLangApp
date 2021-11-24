package tr.com.english.learnlang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.english.learnlang.auth.Token;

public interface TokenDao extends JpaRepository<Token,String> {
}
