package tr.com.english.learnlang.controller.wordController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.responseEntity.ResponseWordCategories;
import tr.com.english.learnlang.entity.responseEntity.WordsWrapper;
import tr.com.english.learnlang.entity.words.Word;
import tr.com.english.learnlang.service.category.CategoryService;
import tr.com.english.learnlang.service.word.WordService;

import java.util.List;
import java.util.Optional;

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


    @GetMapping("/getCategories")
    List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/getCategoriesWithInfo")
    List<ResponseWordCategories> getCategoriesWithWordCount(){
        System.out.println("categories kısmına girildi");
        return  wordService.getResponseCategories();
    }
    @GetMapping("/getWordsWithCategoryName")
    List<Word> getWordsWithCategoryName(@RequestParam("categoryName") String categoryName){
        System.out.println("getWordsWithCategoryName fun cağrıldı");
        return  wordService.getWordsByCategoryName(categoryName);
    }

    @GetMapping("/getWordsByCategoryNameAndWithPage/{categoryName}")
    List<Word> getWordsWithPagination(@PathVariable("categoryName") String categoryName,@RequestParam("pageNumber") int pageNumber,@RequestParam("limit") int limit){
        System.out.println("getWordsWithPagination kısmına girildi"+wordService.getWordsByCategoryNameAndpageble(categoryName,pageNumber,limit));
        return  wordService.getWordsByCategoryNameAndpageble(categoryName,pageNumber,limit);
    }
    @PostMapping("/addCategories")
    void addCategories(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    @PostMapping("/word/getWordByWordListId")
    List<Word> getWordByWordListId(@RequestBody WordsWrapper wrapper){
        if(wrapper.getWordIds().size()>0){
            return wordService.getWordsByWordsListId(wrapper.getWordIds());
        }
        return null;
    }

}
