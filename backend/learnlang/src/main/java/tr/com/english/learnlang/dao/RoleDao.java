package tr.com.english.learnlang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.english.learnlang.entity.role.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
