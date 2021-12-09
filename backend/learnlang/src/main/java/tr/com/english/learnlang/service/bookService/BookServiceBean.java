package tr.com.english.learnlang.service.bookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.dao.BookDao;
import tr.com.english.learnlang.dao.LibraryDao;
import tr.com.english.learnlang.entity.library.Book;
import tr.com.english.learnlang.entity.library.Library;

import java.util.ArrayList;

@Service
public class BookServiceBean  implements  BookService{
    @Autowired
    private LibraryDao libraryDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public void addBookToLibrary(Book book, String libraryName) {
        Library library=libraryDao.getLibraryByLibraryName(libraryName);
        if(library!=null){
            if(library.getBooks().size()==0){
                library.setBooks(new ArrayList<>());
                library.getBooks().add(book);
                return;
            }
            library.getBooks().add(book);
            return;
        }else{
            Library library1=new Library(libraryName=libraryName,"Public Library");
            libraryDao.save(library1);
            if(library1.getBooks().size()==0){
                library1.setBooks(new ArrayList<>());
                library1.getBooks().add(book);
                return;
            }
            library1.getBooks().add(book);
            return;
        }


    }
}
