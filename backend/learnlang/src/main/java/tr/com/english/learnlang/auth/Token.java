package tr.com.english.learnlang.auth;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import tr.com.english.learnlang.entity.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Token {
    @Id
    private String token;
    @ManyToOne
    private User user;
    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Date created;
}
