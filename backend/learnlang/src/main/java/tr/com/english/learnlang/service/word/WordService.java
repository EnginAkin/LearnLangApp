package tr.com.english.learnlang.service.word;

import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.responseEntity.ResponseWordCategories;
import tr.com.english.learnlang.entity.words.Word;

import java.util.List;
import java.util.Optional;

public interface WordService {
    List<Word> getWords();
    List<Word> getWordsByCategoryName(String categoryName);
    List<Category> getCategories();
    List<Word> getWordsByCategoryNameAndpageble(String categoryName,int pageNumber,int limit);
    void addWord(Word word);
    List<Word> getListWordsByUserId(Long id);

    List<ResponseWordCategories> getResponseCategories();

    List<Word> getLearnedWordsByUserId(Long id);

    void addWordToUserLearnedList(Long userId, Long wordId);

    void addUserWordList(Long userId, Long wordId);

    void deleteUserWordListByWordId(Long userId, Long wordId);

    List<Word> getWordsByWordsListId(List<String> wordIds);

    List<Word> getThreeWrongWordAnswer(Long id);
}
