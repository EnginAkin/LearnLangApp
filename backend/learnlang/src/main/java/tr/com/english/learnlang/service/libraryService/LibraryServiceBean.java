package tr.com.english.learnlang.service.libraryService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.dao.BookDao;
import tr.com.english.learnlang.dao.LibraryDao;
import tr.com.english.learnlang.entity.library.Book;
import tr.com.english.learnlang.entity.library.Library;

import java.util.ArrayList;

@Service
public class LibraryServiceBean implements LibraryService{
    @Autowired
    private LibraryDao libraryDao;
    @Autowired
    private BookDao bookDao;
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public Object[] getBooksFromLibraryName(Library library) throws JsonProcessingException {

        if(library!=null) {
            Book book=bookDao.getById(1L);
            System.out.println("girdik 1: "+book);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(book));
            System.out.println("girdik 2");
            System.out.println("size "+library.getBooks().size());
            System.out.println("class name : "+library.getBooks().get(0).getClass().getName());
            return library.getBooks().toArray();
        }
        System.out.println("library null");
        return  null;
    }

    @Override
    public Library getLibraryByName(String libraryName) {

        Library library= libraryDao.getLibraryByLibraryName(libraryName);
        if(library==null){
            Library library1=new Library(libraryName="EngBook","Public Library");
            libraryDao.save(library1);
                    return library1;
        }else {
            return library;
        }
    }

    @Override
    public void addLibraryBook(Library library, Book book) {
        if(library.getBooks()!=null){
            library.getBooks().add(book);
            return;
        }
        library.setBooks(new ArrayList<Book>());
        library.getBooks().add(book);
        return;


    }

    @Override
    public void add(Library library) {
        libraryDao.save(library);

    }


}
