package tr.com.english.learnlang.service.word;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.dao.CategoryDao;
import tr.com.english.learnlang.dao.UserDao;
import tr.com.english.learnlang.dao.WordDao;
import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.responseEntity.ResponseWordCategories;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.words.Word;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceBean implements WordService {
    @Autowired
    private WordDao wordDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UserDao userDao;


    @Override
    public List<Word> getWords() {
        return wordDao.findAll();
    }

    @Override
    public List<Word> getWordsByCategoryName(String categoryName) {
        return wordDao.getWordsByCategoryCategoryName(categoryName);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.findAll();
    }

    @Override
    public List<Word> getWordsByCategoryNameAndpageble(String categoryName, int pageNumber, int limit) {
        List<Word> wordlist = wordDao.getWordsByCategoryCategoryName(categoryName);
        // page number 0 - 10 get 0-10
        // page number 1 - 10 get 10-20...
        // pahe number  3-10    get 30 - 40

        System.out.println("category name = " + categoryName + " page numbber = " + pageNumber + " limit = " + limit);
        if (limit == 0 || limit < 0) limit = 10;
        pageNumber = limit * pageNumber;
        limit = pageNumber + limit;
        if (wordlist.size() < limit) {
            System.out.println("Limit list size : " + wordlist.size());
            return wordlist.subList(pageNumber, wordlist.size());
        }
        return wordlist.subList(pageNumber, limit);
    }

    @Override
    public void addWordToUser(Long wordId, Long userId) {
        User user = userDao.getById(userId);
        Word word = wordDao.getById(wordId);
        if (user.getKelimeList() != null) {
            user.getKelimeList().add(word);
        } else {
            user.setKelimeList(new ArrayList<Word>());
            user.getKelimeList().add(word);
        }
        userDao.save(user);
    }

    @Override
    public void addWord(Word word) {
        Long categoryId = word.getCategory().getId();
        Category category = categoryDao.getById(categoryId);
        word.setCategory(category);
        wordDao.save(word);
    }

    @Override
    public List<Word> getListWordsByUserId(Long id) {
        if (userDao.getById(id).getKelimeList().size() > 0) {
            return userDao.getById(id).getKelimeList();
        }
        return null;
    }

    @Override
    public List<ResponseWordCategories> getResponseCategories() {
        List<Category> categories = getCategories();
        List<ResponseWordCategories> responseWordCategories = new ArrayList<>();
        int wordCount = 0;
        for (Category category : categories) {
            wordCount = getWordsByCategoryName(category.getCategoryName()).size();
            if (wordCount == 0) {
                responseWordCategories.add(new ResponseWordCategories(
                        category.getCategoryName(),
                        category.getCategoryDescription(),
                        0, 0
                ));
            } else {
                responseWordCategories.add(new ResponseWordCategories(
                        category.getCategoryName(),
                        category.getCategoryDescription(),
                        wordCount, wordCount / 10
                ));
            }
        }
        return responseWordCategories;
    }

    @Override
    public List<Word> getLearnedWordsByUserId(Long id) {
        User user = userDao.getById(id);
        if (user != null) {
            if (user.getLearnedWordList().size() > 0) {
                return user.getLearnedWordList();
            }
            return null;
        } else {
            return null;

        }
    }

    @Override
    public void addWordToUserLearnedList(Long userId, Long wordId) {
        User user = userDao.getById(userId);
        Word word = wordDao.getById(wordId);

        if (user != null) {
            System.out.println("user name : "+user.getUsername());
            System.out.println("word name : "+word.getName());
            if (user.getLearnedWordList() != null ) {
                if(!user.getLearnedWordList().contains(word)){
                    user.getLearnedWordList().add(word);

                }
            }else{
                user.setLearnedWordList(new ArrayList<>());
                user.getLearnedWordList().add(word);
            }
            userDao.save(user);

        }
        return;
    }
}
