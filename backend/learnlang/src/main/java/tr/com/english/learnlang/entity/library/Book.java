package tr.com.english.learnlang.entity.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String bookName;
    @Lob
    @Column(columnDefinition="TEXT")
    private String bookDescription;
    private String readTime;


    @Lob
    @Column(columnDefinition="TEXT")
    private String content;

}
