package tr.com.english.learnlang.controller.libraryController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.english.learnlang.constant.GeneralResponse;
import tr.com.english.learnlang.entity.library.Book;
import tr.com.english.learnlang.entity.library.Library;
import tr.com.english.learnlang.service.bookService.BookService;
import tr.com.english.learnlang.service.libraryService.LibraryService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private BookService bookService;

    @PostMapping("/library/addbook/{libraryName}")
    public GeneralResponse addBokToLibrary(@PathVariable(name = "libraryName") String libraryName, @RequestBody Book book){
        try {
            bookService.addBookToLibrary(book,libraryName);
            return new GeneralResponse("başarılı",true);

        }catch (Exception  e){
            return new GeneralResponse("Başarısız",false);
        }
    }
    @GetMapping("/library/addbook1")
    public GeneralResponse addbokMyfunction(){
        try {
            // book name EngKitap
            return new GeneralResponse("başarılı",true);
        }catch (Exception  e){
            return new GeneralResponse("Başarısız"+e.getLocalizedMessage(),false);
        }
    }
    @GetMapping("/library/get/{name}")
    public ResponseEntity<String> getLibraryWithBooks(@PathVariable(name = "name") String libraryName){
        if(libraryName==null || libraryName == ""){
            libraryName="EngBook";
        }
        try {
            Library library=libraryService.getLibraryByName(libraryName);
            Object[] listBooks=libraryService.getBooksFromLibraryName(library);
            System.out.println(" bookname : "+ Arrays.stream(listBooks).count());
            return  ResponseEntity.ok("asda");
        }catch (Exception  e){
            return  ResponseEntity.badRequest().build();
        }
    }


}