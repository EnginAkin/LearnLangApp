package tr.com.english.learnlang.service.role;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.dao.RoleDao;
import tr.com.english.learnlang.entity.role.Role;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceBean implements  RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role = {} to database ",role);
        return roleDao.save(role);
    }

    @Override
    public Role findRoleByName(String rolename) {
        log.info("get  Role by {} in database ",rolename);
        return roleDao.findByName(rolename);
    }
}
