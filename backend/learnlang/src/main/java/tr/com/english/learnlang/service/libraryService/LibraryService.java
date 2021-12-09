package tr.com.english.learnlang.service.libraryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import tr.com.english.learnlang.entity.library.Book;
import tr.com.english.learnlang.entity.library.Library;

public interface LibraryService {

    Object[] getBooksFromLibraryName(Library library) throws JsonProcessingException;
    Library getLibraryByName(String libraryName);
    void addLibraryBook(Library library, Book book);
    void add(Library library);


}
