package tr.com.english.learnlang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.english.learnlang.entity.library.Book;

public interface BookDao extends JpaRepository<Book,Long> {
}
