package tr.com.english.learnlang.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.com.english.learnlang.entity.user.User;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class JwtUserDetails implements UserDetails {
    private long id ;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtUserDetails(long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public static JwtUserDetails userToJwtUser(User user){
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(),authorities);

    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
