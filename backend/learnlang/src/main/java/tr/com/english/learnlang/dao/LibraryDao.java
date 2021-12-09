package tr.com.english.learnlang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.english.learnlang.entity.library.Book;
import tr.com.english.learnlang.entity.library.Library;

import java.util.List;

public interface LibraryDao extends JpaRepository<Library,Long> {

    Library getLibraryByLibraryName(String libraryName);
}
