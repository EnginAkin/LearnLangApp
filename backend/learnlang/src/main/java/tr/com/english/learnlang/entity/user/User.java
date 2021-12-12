package tr.com.english.learnlang.entity.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.english.learnlang.entity.role.Role;
import tr.com.english.learnlang.entity.words.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String password;
    private Double score;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Word> kelimeList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Word> learnedWordList;

}
