package tr.com.english.learnlang.service.bookService;

import tr.com.english.learnlang.entity.library.Book;
import tr.com.english.learnlang.entity.library.Library;

public interface BookService {
    void addBookToLibrary(Book book, String libraryName);
}
