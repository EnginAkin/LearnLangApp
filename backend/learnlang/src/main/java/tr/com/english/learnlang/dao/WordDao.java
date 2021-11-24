package tr.com.english.learnlang.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.english.learnlang.entity.words.Word;

import java.util.List;

@Repository
public interface WordDao extends JpaRepository<Word,Long> {
        List<Word> getWordsByCategoryCategoryName(String categoryName);


}
