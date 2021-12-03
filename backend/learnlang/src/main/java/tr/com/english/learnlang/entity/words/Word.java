package tr.com.english.learnlang.entity.words;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.english.learnlang.entity.category.Category;
import tr.com.english.learnlang.entity.sentence.Sentence;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mean;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Sentence> exampleSentence;
    @ManyToOne(cascade =CascadeType.ALL )
    private Category category;


}
