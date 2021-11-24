package tr.com.english.learnlang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.english.learnlang.entity.sentence.Sentence;

@Repository
public interface SentenceDao extends JpaRepository<Sentence,Long> {
}
