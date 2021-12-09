package tr.com.english.learnlang.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.english.learnlang.entity.words.Word;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private String libraryName;
    private String libraryCategoryName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books;

    public Library(String libraryName, String libraryCategoryName) {
        this.libraryName = libraryName;
        this.libraryCategoryName = libraryCategoryName;
    }
}
