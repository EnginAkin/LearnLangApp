package tr.com.english.learnlang.controller.wordController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.dao.WordDao;
import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.user.User;
import tr.com.english.learnlang.entity.words.Word;
import tr.com.english.learnlang.service.category.CategoryService;
import tr.com.english.learnlang.service.user.UserService;
import tr.com.english.learnlang.service.word.WordService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WordController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private WordService wordService;

    @PostMapping("/addWord")
    public void addWord(@RequestBody Word word){
        wordService.addWord(word);
    }

    @GetMapping("/getWords")
    public List<Word> getWords(){
        return wordService.getWords();
    }
    @PostMapping("/addWordToUser/{wordId}/{userId}")
    public void addWordToUser(@PathVariable("wordId") Long wordId,@PathVariable("userId") Long userId){
       wordService.addWordToUser(wordId,userId);
    }

    @GetMapping("/getCategories")
    List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/getCategoriesWithInfo")
    GeneralResponse getCategoriesWithWordCount(){
        return new GeneralResponse("başarılı",true,wordService.getResponseCategories());
    }

    @GetMapping("/getWordsByCategoryNameAndWithPage/{categoryName}/{pageNumber}/{limit}")
    List<Word> getWordsWithPagination(@PathVariable("categoryName") String categoryName,@PathVariable("pageNumber") int pageNumber,@PathVariable("limit") int limit){
        return  wordService.getWordsByCategoryNameAndpageble(categoryName,pageNumber,limit);
    }
    @PostMapping("/addCategories")
    void addCategories(@RequestBody Category category){
        categoryService.addCategory(category);
    }


}
