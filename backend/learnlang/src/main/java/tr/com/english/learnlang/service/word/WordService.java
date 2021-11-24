package tr.com.english.learnlang.service.word;

import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.words.Word;

import java.util.List;

public interface WordService {
    List<Word> getWords();
    List<Word> getWordsByCategoryName(String categoryName);
    List<Category> getCategories();
    List<Word> getWordsByCategoryNameAndpageble(String categoryName,int pageNumber,int limit);
    void addWordToUser(Long wordId,Long userId);
    void addWord(Word word);

    List<Word> getWordsByUserId(Long id);
}
