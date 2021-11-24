package tr.com.english.learnlang.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import tr.com.english.learnlang.entity.words.Word;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private Double score;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Word> kelimeList;
}
